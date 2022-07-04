import { useContext, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { fetchDetails, resetCollection } from "../context/Actions";
import StartSprintForm from "../components/StartSprintForm";

import { Spin, Descriptions, Button, Row, Col } from "antd";
import {
  PlayCircleOutlined,
  UnorderedListOutlined,
  FundProjectionScreenOutlined,
  CaretLeftOutlined,
} from "@ant-design/icons";

export default function SprintDetails() {
  const { state, dispatch } = useContext(AppContext);
  const { details } = state;
  const { loading, error, data } = details;
  const [startForm, setStartForm] = useState(false);
  const [update, setUpdate] = useState(false);

  const navigate = useNavigate();
  const location = useLocation();

  const id = location.state.id;

  // GET REQUEST TO API
  useEffect(() => {
    const url = `${URL_API}/sprints/${id}`;
    const request = {};
    fetchDetails(url, request, dispatch);
    // eslint-disable-next-line
  }, [update]);

  const startSprint = () => {
    setStartForm(true);
  };

  const seeScrumBoard = () => {
    resetCollection(dispatch);
    navigate("scrumboard", {
      state: { sprint: id, project: location.state.project },
      replace: true,
    });
  };

  const seeTasks = () => {
    resetCollection(dispatch);
    navigate("tasks", {
      state: { project: id },
      replace: true,
    });
  };

  const back = () => {
    resetCollection(dispatch);
    navigate("../projects/projectdetails/sprints", {
      state: { project: location.state.project },
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
      if (data.length === 1) {
        let details = data[0];

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
                  onClick={startSprint}
                  icon={<PlayCircleOutlined />}
                >
                  Start
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={seeScrumBoard}
                  icon={<UnorderedListOutlined />}
                >
                  Sprint Backlog
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={seeTasks}
                  icon={<FundProjectionScreenOutlined />}
                >
                  Tasks
                </Button>
              </Col>
            </Row>
          );
        };

        const getStartForm = () => {
          if (startForm) {
            return (
              <StartSprintForm
                visible={startForm}
                onClose={setStartForm}
                update={setUpdate}
                data={details}
              />
            );
          }
        };

        return (
          <>
            {getStartForm()}

            <Descriptions
              title={details.name.toUpperCase()}
              bordered
              extra={allowedButtons()}
            >
              <Descriptions.Item label="Project">
                {details.projectID}
              </Descriptions.Item>
              <Descriptions.Item label="Name" span={2}>
                {details.name}
              </Descriptions.Item>
              <Descriptions.Item label="Start Date">
                {details.startDate}
              </Descriptions.Item>
              <Descriptions.Item label="End Date">
                {details.endDate}
              </Descriptions.Item>
            </Descriptions>
          </>
        );
      }
    }
  }
}
