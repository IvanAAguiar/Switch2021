import { useContext, useEffect, useState } from "react";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import { fetchCollections, resetDetails } from "../context/Actions";

import { Col, Space, Row, Spin, Button } from "antd";
import Search from "antd/lib/input/Search";
import { PlusOutlined, CaretLeftOutlined } from "@ant-design/icons";

import TableCollections from "../components/TableCollections";
import CreateSprintForm from "../components/CreateSprintForm";
import { useLocation, useNavigate } from "react-router-dom";

export default function Sprints() {
  const { state, dispatch } = useContext(AppContext);
  const { collection, details } = state;
  const { loading, error, data } = collection;

  const navigate = useNavigate();
  const location = useLocation();

  const [search, setSearch] = useState("");
  const [sprintForm, setSprintForm] = useState(false);
  const [update, setUpdate] = useState(false);

  const handleButtonClick = (target) => {
    resetDetails(dispatch);
    navigate("sprintdetails", {
      state: {
        id: location.state.project + "&" + target.name,
        title: target.name,
        project: location.state.project,
      },
      replace: true,
    });
  };

  const columns = [
    {
      title: "ID",
      dataIndex: "key",

      sorter: (a, b) => ("" + a.key).localeCompare(b.key),
    },
    {
      title: "Name",
      dataIndex: "name",
      sorter: (a, b) => ("" + a.name).localeCompare(b.name),
    },
    {
      title: "Start Date",
      dataIndex: "start",
      defaultSortOrder: "ascend",
      sorter: (a, b) => ("" + a.start).localeCompare(b.start),
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
    let url = `${URL_API}/sprints/sprintsList/${location.state.project}`;

    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, [update]);

  const onSearch = (value) => {
    setSearch(value);
  };

  const searchSprints = () => {
    return (
      <Search
        style={{
          width: 300,
          marginBottom: 10,
        }}
        placeholder="Search for Sprints Here"
        onSearch={onSearch}
        enterButton
      />
    );
  };

  const createSprint = () => {
    setSprintForm(true);
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

        if (Object.keys(data[0])[0] === "_embedded") {
          internal = data[0]._embedded.outSprintDTOList.map((sprint, idx) => {
            return {
              key: idx,
              name: sprint.name,
              start: sprint.startDate,
            };
          });
        }

        if (search !== "") {
          internal = internal.filter((data) => {
            const toCheckName = JSON.stringify(data.name).toLowerCase().trim();
            const toCheckDate = JSON.stringify(data.start).toLowerCase().trim();

            const searchSprints = search.toLowerCase().trim();
            return (
              toCheckName.includes(searchSprints) ||
              toCheckDate.includes(searchSprints)
            );
          });
        }

        const getCreateSprintForm = () => {
          if (sprintForm) {
            return (
              <CreateSprintForm
                visible={sprintForm}
                onClose={setSprintForm}
                update={setUpdate}
                data={details.data[0]}
                project={location.state.project}
              />
            );
          }
        };

        return (
          <>
            {getCreateSprintForm()}
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
                  onClick={createSprint}
                  icon={<PlusOutlined />}
                >
                  Create
                </Button>
              </Col>
              <Col>{searchSprints()}</Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <TableCollections
                  title="Sprint List"
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
