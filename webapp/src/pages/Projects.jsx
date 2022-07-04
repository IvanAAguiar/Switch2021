import { useContext, useEffect, useState } from "react";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import { fetchCollections, resetDetails } from "../context/Actions";

import { Col, Space, Row, Spin, Button } from "antd";
import Search from "antd/lib/input/Search";
import { PlusOutlined } from "@ant-design/icons";

import TableCollections from "../components/TableCollections";
import CreateProjectForm from "../components/CreateProjectForm";
import { useNavigate } from "react-router-dom";

export default function Projects() {
  const { state, dispatch } = useContext(AppContext);
  const { collection } = state;
  const { loading, error, data } = collection;
  const navigate = useNavigate();

  const [search, setSearch] = useState("");
  const [externalSearch, setExternalSearch] = useState("");
  const [projectForm, setProjectForm] = useState(false);

  const handleButtonClick = (target) => {
    resetDetails(dispatch);
    navigate("projectdetails", { state: { code: target.key }, replace: true });
  };

  const handleButtonClickDelete = () => {
    console.log("Deleted");
  };

  const columns = [
    {
      title: "Code",
      dataIndex: "key",

      sorter: (a, b) => ("" + a.key).localeCompare(b.key),
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

  const columnsExt = [
    {
      title: "Code",
      dataIndex: "key",

      sorter: (a, b) => ("" + a.key).localeCompare(b.key),
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
  ];

  useEffect(() => {
    let url = `${URL_API}/projects`;

    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, [projectForm]);

  const onSearch = (value) => {
    setSearch(value);
  };

  const onSearchExternal = (value) => {
    setExternalSearch(value);
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

  const searchProjectsExternal = () => {
    return (
      <Search
        style={{
          width: 300,
          marginBottom: 10,
        }}
        placeholder="Search for Project Here"
        onSearch={onSearchExternal}
        enterButton
      />
    );
  };

  const createProject = () => {
    setProjectForm(true);
  };

  const getCreateProjectForm = () => {
    if (projectForm) {
      return (
        <CreateProjectForm visible={projectForm} onClose={setProjectForm} />
      );
    }
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
      if (data[0].internalProjects !== undefined) {
        let internal = data[0].internalProjects.content.map((project) => {
          return {
            key: project.code,
            name: project.projectName,
            description: project.description,
            start: project.startDate,
            status: project.projectStatus,
          };
        });
        let external = data[0].externalProjects.content.map((project) => {
          return {
            key: project.code,
            name: project.projectName,
            description: project.description,
            start: project.startDate,
            status: project.projectStatus,
          };
        });

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

        if (externalSearch !== undefined) {
          external = external.filter((data) => {
            const toCheckName = JSON.stringify(data.name).toLowerCase().trim();
            const toCheckCode = JSON.stringify(data.key).toLowerCase().trim();
            const toCheckStatus = JSON.stringify(data.status)
              .toLowerCase()
              .trim();

            const searchProjects = externalSearch.toLowerCase().trim();
            return (
              toCheckName.includes(searchProjects) ||
              toCheckCode.includes(searchProjects) ||
              toCheckStatus.includes(searchProjects)
            );
          });
        }

        return (
          <>
            {getCreateProjectForm()}
            <Row gutter={16}>
              <Col span={2}>
                <Button
                  type="primary"
                  onClick={createProject}
                  icon={<PlusOutlined />}
                >
                  Create
                </Button>
              </Col>
              <Col span={10}>{searchProjects()}</Col>

              <Col span={12}>{searchProjectsExternal()}</Col>
            </Row>
            <Row gutter={16}>
              <Col span={12}>
                <TableCollections
                  title="Internal"
                  data={internal}
                  header={columns}
                />
              </Col>
              <Col span={12}>
                <TableCollections
                  title="External"
                  data={external}
                  header={columnsExt}
                />
              </Col>
            </Row>
          </>
        );
      }
    }
  }
}
