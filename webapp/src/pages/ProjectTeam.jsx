import { useContext, useEffect, useState } from "react";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import { fetchCollections, resetDetails } from "../context/Actions";

import { Col, Space, Row, Spin, Button } from "antd";
import Search from "antd/lib/input/Search";
import { PlusOutlined, CaretLeftOutlined } from "@ant-design/icons";

import TableCollections from "../components/TableCollections";
import CreateResourceForm from "../components/CreateResourceForm";
import { useLocation, useNavigate } from "react-router-dom";

const rex = /([A-Z])([A-Z])([a-z])|([a-z])([A-Z])/g;

export default function ProjectTeam() {
  const { state, dispatch } = useContext(AppContext);
  const { collection, details } = state;
  const { loading, error, data } = collection;

  const navigate = useNavigate();
  const location = useLocation();

  const [search, setSearch] = useState("");
  const [resourceForm, setResourceForm] = useState(false);
  const [update, setUpdate] = useState(false);

  const handleButtonClick = (target) => {
    resetDetails(dispatch);
    navigate("resourcedetails", {
      state: {
        user: target.key,
        project: location.state.project,
        start: target.startDate,
        endDateProject: location.state.endDate,
      },
      replace: true,
    });
  };

  const columns = [
    {
      title: "User",
      dataIndex: "key",

      sorter: (a, b) => ("" + a.key).localeCompare(b.key),
    },
    {
      title: "Role",
      dataIndex: "role",
      sorter: (a, b) => ("" + a.role).localeCompare(b.role),
    },
    {
      title: "Start Date",
      dataIndex: "startDate",
      sorter: (a, b) => ("" + a.startDate).localeCompare(b.startDate),
    },
    {
      title: "End Date",
      dataIndex: "endDate",
      sorter: (a, b) => ("" + a.endDate).localeCompare(b.endDate),
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
    let url = `${URL_API}/resources/currentprojectTeam/${location.state.project}`;

    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, [update]);

  const onSearch = (value) => {
    setSearch(value);
  };

  const searchResources = () => {
    return (
      <Search
        style={{
          width: 300,
          marginBottom: 10,
        }}
        placeholder="Search for Resource Here"
        onSearch={onSearch}
        enterButton
      />
    );
  };

  const createResource = () => {
    setResourceForm(true);
  };

  const back = () => {
    resetDetails(dispatch);
    navigate("../projects/projectdetails", {
      state: { code: location.state.project },
      replace: true,
    });
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
          internal = data[0]._embedded.outputResourceDTOList.map((resource) => {
            return {
              key: resource.user,
              role: resource.role.replace(rex, "$1$4 $2$3$5"),
              startDate: resource.startDate,
              endDate: resource.endDate,
            };
          });
        }

        if (search !== "") {
          internal = internal.filter((data) => {
            const toCheckUser = JSON.stringify(data.key).toLowerCase().trim();
            const toCheckRole = JSON.stringify(data.role).toLowerCase().trim();

            const searchResources = search.toLowerCase().trim();
            return (
              toCheckUser.includes(searchResources) ||
              toCheckRole.includes(searchResources)
            );
          });
        }

        const getCreateResourceForm = () => {
          if (resourceForm) {
            return (
              <CreateResourceForm
                visible={resourceForm}
                onClose={setResourceForm}
                update={setUpdate}
                data={details.data[0]}
              />
            );
          }
        };

        return (
          <>
            {getCreateResourceForm()}
            <Row gutter={16}>
              <Col>
                <Button
                  ghost
                  type="primary"
                  onClick={back}
                  icon={<CaretLeftOutlined />}
                >
                  Back
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={createResource}
                  icon={<PlusOutlined />}
                >
                  Assign User
                </Button>
              </Col>
              <Col>{searchResources()}</Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <TableCollections
                  title="Project Team"
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
