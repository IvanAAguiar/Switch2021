import { useContext, useEffect, useState } from "react";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import { fetchCollections, resetCollection } from "../context/Actions";

import { Col, Space, Row, Spin, Button } from "antd";
import Search from "antd/lib/input/Search";
import {
  PlusOutlined,
  AppstoreOutlined,
  CaretLeftOutlined,
} from "@ant-design/icons";

import TableCollections from "../components/TableCollections";
import { useLocation, useNavigate } from "react-router-dom";
import AddUserStoryForm from "../components/AddUserStoryForm";

export default function ScrumBoard() {
  const { state, dispatch } = useContext(AppContext);
  const { collection, details } = state;
  const { loading, error, data } = collection;
  const navigate = useNavigate();
  const location = useLocation();

  const [search, setSearch] = useState("");
  const [userStoryForm, setUserStoryForm] = useState(false);
  const [update, setUpdate] = useState(false);

  const handleButtonClick = (target) => {
    resetCollection(dispatch);
    navigate("tasks", {
      state: {
        id: details.data[0].code + "&" + target.key,
        title: target.key,
        project: details.data[0].code,
      },
      replace: true,
    });
  };

  const handleButtonClickStatus = () => {
    const sprintBacklog = data[0];
    navigate("categories", {
      state: {
        data: sprintBacklog,
        sprint: location.state.sprint,
        project: location.state.project,
      },
      replace: true,
    });
  };

  const columns = [
    {
      title: "Sprint",
      dataIndex: "sprint",
      sorter: (a, b) => ("" + a.sprint).localeCompare(b.sprint),
    },
    {
      title: "User Story",
      dataIndex: "key",
      sorter: (a, b) => ("" + a.key).localeCompare(b.key),
    },
    {
      title: "Status",
      dataIndex: "status",
      defaultSortOrder: "ascend",
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
            Tasks
          </button>
        </Space>
      ),
    },
  ];

  useEffect(() => {
    let url = `${URL_API}/sprints/scrumboard/${location.state.sprint}`;

    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, [update]);

  const onSearch = (value) => {
    setSearch(value);
  };

  const searchUserStories = () => {
    return (
      <Search
        style={{
          width: 300,
          marginBottom: 10,
        }}
        placeholder="Search for User Story Here"
        onSearch={onSearch}
        enterButton
      />
    );
  };

  const createUserStory = () => {
    setUserStoryForm(true);
  };

  const back = () => {
    navigate("../projects/projectdetails/sprints/sprintdetails", {
      state: { id: location.state.sprint, project: location.state.project },
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

        if (Object.keys(data[0]).length === 1) {
          internal = data[0]._embedded[Object.keys(data[0]._embedded)[0]].map(
            (resource) => {
              return {
                key: resource.usTitle,
                sprint: resource.sprintName,
                status: resource.status,
              };
            }
          );
        }

        if (search !== "") {
          internal = internal.filter((data) => {
            const toCheckTitle = JSON.stringify(data.key).toLowerCase().trim();
            const toCheckStatus = JSON.stringify(data.status)
              .toLowerCase()
              .trim();

            const searchResources = search.toLowerCase().trim();
            return (
              toCheckTitle.includes(searchResources) ||
              toCheckStatus.includes(searchResources)
            );
          });
        }

        const getCreateUserStoryForm = () => {
          if (userStoryForm) {
            return (
              <AddUserStoryForm
                visible={userStoryForm}
                onClose={setUserStoryForm}
                update={setUpdate}
                project={location.state.project}
                sprint={location.state.sprint}
                data={data[0]}
              />
            );
          }
        };

        return (
          <>
            {getCreateUserStoryForm()}
            <Row gutter={24}>
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
                  onClick={createUserStory}
                  icon={<PlusOutlined />}
                >
                  Add
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={handleButtonClickStatus}
                  icon={<AppstoreOutlined />}
                  style={{ backgroundColor: "#006d75" }}
                >
                  Scrum Board
                </Button>
              </Col>
              <Col>{searchUserStories()}</Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <TableCollections
                  title="Sprint Backlog"
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
