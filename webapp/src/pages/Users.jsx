import { useLocation, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import { fetchCollections, resetDetails } from "../context/Actions";

import { Space, Spin } from "antd";
import Search from "antd/lib/input/Search";

import TableCollections from "../components/TableCollections";

function getUser(key, name, email, func) {
  return {
    key,
    name,
    email,
    function: func,
  };
}

export default function Users() {
  const { state, dispatch } = useContext(AppContext);
  const { collection } = state;
  const { loading, error, data } = collection;
  const navigate = useNavigate();
  const location = useLocation();

  const [search, setSearch] = useState(false);
  const [textSearch, setTextSearch] = useState("");

  const handleButtonClick = (target) => {
    resetDetails(dispatch);
    navigate("userDetails", { state: { email: target.email }, replace: true });
  };

  const handleButtonClickDelete = () => {
    console.log("Deleted");
  };

  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      defaultSortOrder: "ascend",
      sorter: (a, b) => ("" + a.name).localeCompare(b.name),
    },
    {
      title: "E-mail",
      dataIndex: "email",

      sorter: (a, b) => ("" + a.email).localeCompare(b.email),
    },
    {
      title: "Function",
      dataIndex: "function",
      sorter: (a, b) => ("" + a.function).localeCompare(b.function),
    },
    {
      title: "Action",
      key: "action",
      render: (_, record) => (
        <Space size="middle">
          <button
            className="anchor-button"
            onClick={() => handleButtonClick(record)}
          >
            Details
          </button>
          <button
            className="anchor-button"
            onClick={() => handleButtonClickDelete(record)}
          >
            Delete
          </button>
        </Space>
      ),
    },
  ];

  useEffect(() => {
    let url = `${URL_API}/users`;

    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, []);

  const onSearch = (value) => {
    setSearch(true);
    setTextSearch(value);
  };

  const searchUsers = () => {
    return (
      <Search
        style={{
          width: 300,
          marginBottom: 10,
        }}
        placeholder="Search for User Here"
        onSearch={onSearch}
        enterButton
      />
    );
  };

  if (loading === true) {
    return (
      <div className="spin-loading">
        <Spin />
      </div>
    );
  } else {
    if (error !== null) {
      return <h1 style={{ color: "red" }}>{error}</h1>;
    } else {
      if (Object.keys(data[0])[0] === "_embedded") {
        const collect = Object.keys(data[0]._embedded)[0];

        let response = data[0]._embedded[collect].map((user, key) =>
          getUser(key, user.userName, user.email, user.function)
        );

        if (location.pathname === "/") {
          response = response.filter((data) => {
            const toCheckName = JSON.stringify(data.name).toLowerCase().trim();
            const toCheckEmail = JSON.stringify(data.email)
              .toLowerCase()
              .trim();
            const searchUser = location.state.search.toLowerCase().trim();
            return (
              toCheckName.includes(searchUser) ||
              toCheckEmail.includes(searchUser)
            );
          });
        } else {
          if (search) {
            response = response.filter((data) => {
              const toCheckName = JSON.stringify(data.name)
                .toLowerCase()
                .trim();
              const toCheckEmail = JSON.stringify(data.email)
                .toLowerCase()
                .trim();
              const searchUser = textSearch.toLowerCase().trim();
              return (
                toCheckName.includes(searchUser) ||
                toCheckEmail.includes(searchUser)
              );
            });
          }
        }

        return (
          <>
            {searchUsers()}
            <TableCollections
              title="Users"
              data={response}
              header={columns}
            />
          </>
        );
      }
    }
  }
}
