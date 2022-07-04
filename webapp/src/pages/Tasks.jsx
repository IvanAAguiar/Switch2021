import { useContext, useEffect, useState } from "react";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import { fetchCollections, resetDetails } from "../context/Actions";

import { Col, Space, Row, Spin, Button } from "antd";
import Search from "antd/lib/input/Search";
import { PlusOutlined } from "@ant-design/icons";

import TableCollections from "../components/TableCollections";
import CreateUserStoryForm from "../components/CreateUserStoryForm";
import { useLocation, useNavigate } from "react-router-dom";

export default function Tasks() {
  const { state, dispatch } = useContext(AppContext);
  const { collection, details } = state;
  const { loading, error, data } = collection;
  const navigate = useNavigate();
  const location = useLocation();

  const [search, setSearch] = useState("");
  const [userStoryForm, setUserStoryForm] = useState(false);
  const [update, setUpdate] = useState(false);

  const handleButtonClick = (target) => {
    resetDetails(dispatch);
    navigate("userstorydetails", {
      state: {
        id: details.data[0].code + "&" + target.key,
        title: target.key,
        project: details.data[0].code,
      },
      replace: true,
    });
  };

  const columns = [
    {
      title: "Title",
      dataIndex: "key",

      sorter: (a, b) => ("" + a.key).localeCompare(b.key),
    },
    {
      title: "Description",
      dataIndex: "description",
      sorter: (a, b) => ("" + a.description).localeCompare(b.description),
    },
    {
      title: "Priority",
      dataIndex: "priority",
      defaultSortOrder: "ascend",
      sorter: (a, b) => ("" + a.priority).localeCompare(b.priority),
    },
    {
      title: "Time Estimate",
      dataIndex: "timeEstimate",
      sorter: (a, b) => ("" + a.timeEstimate).localeCompare(b.timeEstimate),
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
    let url = `${URL_API}/userstories/productBacklog/${location.state.project}`;

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
          internal = data[0]._embedded[Object.keys(data[0]._embedded)[0]].map(
            (resource) => {
              return {
                key: resource.usTitle,
                description: resource.description,
                priority: resource.priority,
                timeEstimate: resource.timeEstimate,
                status: resource.status,
              };
            }
          );
        }

        if (search !== "") {
          internal = internal.filter((data) => {
            const toCheckTitle = JSON.stringify(data.key).toLowerCase().trim();
            const toCheckPriority = JSON.stringify(data.priority)
              .toLowerCase()
              .trim();
            const toCheckStatus = JSON.stringify(data.status)
              .toLowerCase()
              .trim();

            const searchResources = search.toLowerCase().trim();
            return (
              toCheckTitle.includes(searchResources) ||
              toCheckPriority.includes(searchResources) ||
              toCheckStatus.includes(searchResources)
            );
          });
        }

        const getCreateUserStoryForm = () => {
          if (userStoryForm) {
            return (
              <CreateUserStoryForm
                visible={userStoryForm}
                onClose={setUserStoryForm}
                update={setUpdate}
                data={details.data[0]}
              />
            );
          }
        };

        return (
          <>
            {getCreateUserStoryForm()}
            <Row gutter={16}>
              <Col span={2}>
                <Button
                  type="primary"
                  onClick={createUserStory}
                  icon={<PlusOutlined />}
                >
                  Create
                </Button>
              </Col>
              <Col span={10}>{searchUserStories()}</Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <TableCollections
                  title="Product Backlog"
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
