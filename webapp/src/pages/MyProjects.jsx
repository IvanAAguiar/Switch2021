import { useContext, useEffect, useState } from "react";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import { fetchCollections, resetDetails } from "../context/Actions";

import { Col, Space, Row, Spin } from "antd";
import Search from "antd/lib/input/Search";

import TableCollections from "../components/TableCollections";
import { useNavigate } from "react-router-dom";

export default function MyProjects() {
  const { state, dispatch } = useContext(AppContext);
  const { collection, auth } = state;
  const { loading, error, data } = collection;
  const navigate = useNavigate();

  const [search, setSearch] = useState("");

  const handleButtonClick = (target) => {
    resetDetails(dispatch);
    navigate("../projects/projectdetails", {
      state: { code: target.key, path: "myprojects" },
      replace: true,
    });
  };

  const columns = [
    {
      title: "Code",
      dataIndex: "key",

      sorter: (a, b) => ("" + a.code).localeCompare(b.code),
    },
    {
      title: "Name",
      dataIndex: "name",
      sorter: (a, b) => ("" + a.name).localeCompare(b.name),
    },
    {
      title: "Description",
      dataIndex: "description",
      sorter: (a, b) => ("" + a.description).localeCompare(b.description),
    },
    {
      title: "Start Date",
      dataIndex: "start",
      defaultSortOrder: "descend",
      sorter: (a, b) => ("" + a.start).localeCompare(b.start),
    },
    {
      title: "Status",
      dataIndex: "status",
      sorter: (a, b) => ("" + a.status).localeCompare(b.status),
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
        </Space>
      ),
    },
  ];

  useEffect(() => {
    let url = `${URL_API}/projects/${auth.data.email}/projects`;

    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, []);

  const onSearch = (value) => {
    setSearch(value);
  };

  const searchProjects = () => {
    return (
      <Search
        style={{
          width: 300,
          marginBottom: 10,
        }}
        placeholder="Search for Project Here"
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
      if (Object.keys(data[0]) !== 0) {
        let internal = [];

        if (Object.keys(data[0]).length === 2) {
          internal = data[0]._embedded.Projects.map((project) => {
            return {
              key: project.code,
              name: project.projectName,
              description: project.description,
              start: project.startDate,
              status: project.status,
            };
          });
        }

        if (search !== undefined) {
          internal = internal.filter((data) => {
            const toCheckName = JSON.stringify(data.name).toLowerCase().trim();
            const toCheckCode = JSON.stringify(data.key).toLowerCase().trim();
            const toCheckStatus = JSON.stringify(data.status)
              .toLowerCase()
              .trim();

            const searchProjects = search.toLowerCase().trim();
            return (
              toCheckName.includes(searchProjects) ||
              toCheckCode.includes(searchProjects) ||
              toCheckStatus.includes(searchProjects)
            );
          });
        }

        return (
          <>
            <Row gutter={16}>
              <Col span={10}>{searchProjects()}</Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <TableCollections
                  title="My Projects"
                  data={internal}
                  header={columns}
                />
              </Col>
            </Row>
          </>
        );
      }
    }
  }
}
