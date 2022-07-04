import { useContext, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { fetchDetails, resetCollection } from "../context/Actions";
import EditProjectForm from "../components/EditProjectForm";

import { Spin, Descriptions, Badge, Button, Row, Col } from "antd";
import {
  EditOutlined,
  TeamOutlined,
  ProfileOutlined,
  FundProjectionScreenOutlined,
  CalendarOutlined,
  CaretLeftOutlined,
} from "@ant-design/icons";

export default function ProjectDetails() {
  const { state, dispatch } = useContext(AppContext);
  const { details } = state;
  const { loading, error, data } = details;
  const [editForm, setEditForm] = useState(false);

  const navigate = useNavigate();
  const location = useLocation();

  const id = location.state.code;

  // GET REQUEST TO API
  useEffect(() => {
    const url = `${URL_API}/projects/${id}`;
    const request = {};
    fetchDetails(url, request, dispatch);
    // eslint-disable-next-line
  }, []);

  const editDetails = () => {
    setEditForm(true);
  };

  const seeUserStories = () => {
    resetCollection(dispatch);
    navigate("productbacklog", {
      state: { project: id },
      replace: true,
    });
  };

  const seeSprints = () => {
    resetCollection(dispatch);
    navigate("sprints", {
      state: { project: id },
      replace: true,
    });
  };

  const seeActivities = () => {
    console.log("Activities");
  };

  const back = () => {
    resetCollection(dispatch);
    if (location.state.path === "myprojects") {
      navigate("../myprojects");
    } else {
      navigate("../projects");
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
      if (data.length === 1) {
        let details = data[0];

        const seeProjectTeam = () => {
          resetCollection(dispatch);
          navigate("projectteam", {
            state: { endDate: details.endDate, project: id },
            replace: true,
          });
        };

        const allowedButtons = () => {
          return (
            <Row gutter={8}>
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
                  onClick={editDetails}
                  icon={<EditOutlined />}
                >
                  Edit
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={seeProjectTeam}
                  icon={<TeamOutlined />}
                >
                  Project Team
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={seeUserStories}
                  icon={<ProfileOutlined />}
                >
                  Product Backlog
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={seeSprints}
                  icon={<CalendarOutlined />}
                >
                  Sprints
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={seeActivities}
                  icon={<FundProjectionScreenOutlined />}
                >
                  Activities
                </Button>
              </Col>
            </Row>
          );
        };

        const getEditForm = () => {
          if (editForm) {
            return (
              <EditProjectForm
                visible={editForm}
                onClose={setEditForm}
                data={details}
              />
            );
          }
        };

        const getStatus = () => {
          switch (details.status) {
            case "PLANNED":
              return "default";
            case "INCEPTION":
              return "processing";
            case "ELABORATION":
              return "processing";
            case "CONSTRUCTION":
              return "processing";
            case "TRANSITION":
              return "processing";
            case "WARRANTY":
              return "warning";
            case "CLOSED":
              return "success";
            default:
              return "default";
          }
        };

        return (
          <>
            {getEditForm()}

            <Descriptions
              title={details.projectName}
              bordered
              extra={allowedButtons()}
            >
              <Descriptions.Item label="Code">{details.code}</Descriptions.Item>
              <Descriptions.Item label="Name">
                {details.projectName}
              </Descriptions.Item>
              <Descriptions.Item label="Status">
                <Badge status={getStatus()} text={details.status} />
              </Descriptions.Item>
              <Descriptions.Item label="Description" span={3}>
                {details.description}
              </Descriptions.Item>
              <Descriptions.Item label="Start Date">
                {details.startDate}
              </Descriptions.Item>
              <Descriptions.Item label="End Date">
                {details.endDate}
              </Descriptions.Item>
              <Descriptions.Item label="Typology">
                {details.typo}
              </Descriptions.Item>
              <Descriptions.Item label="Customer">
                {details.customer}
              </Descriptions.Item>
              <Descriptions.Item label="Business Sector">
                {details.businessSector}
              </Descriptions.Item>

              <Descriptions.Item label="Budget">
                {details.budget + "€"}
              </Descriptions.Item>
              <Descriptions.Item label="Number of Sprints">
                {details.numberOfSprints}
              </Descriptions.Item>
              <Descriptions.Item label="Sprint Duration">
                {details.sprintDuration + " days"}
              </Descriptions.Item>
            </Descriptions>
          </>
        );
      }
    }
  }
}
