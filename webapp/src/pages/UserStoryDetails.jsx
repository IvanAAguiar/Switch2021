import { useContext, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { fetchDetails, resetCollection } from "../context/Actions";
import PriorityForm from "../components/PriorityForm";
import TimeEstimateForm from "../components/TimeEstimateForm";
import RefineForm from "../components/RefineForm";

import { Spin, Descriptions, Badge, Button, Row, Col } from "antd";
import {
  ClockCircleOutlined,
  OrderedListOutlined,
  ApartmentOutlined,
  CaretLeftOutlined,
} from "@ant-design/icons";

export default function UserStoryDetails() {
  const { state, dispatch } = useContext(AppContext);
  const { details } = state;
  const { loading, error, data } = details;
  const [effortForm, setEffortForm] = useState(false);
  const [priorityForm, setPriorityForm] = useState(false);
  const [refineForm, setRefineForm] = useState(false);
  const [update, setUpdate] = useState(false);

  const navigate = useNavigate();
  const location = useLocation();

  const id = location.state.id;

  // GET REQUEST TO API
  useEffect(() => {
    const url = `${URL_API}/userstories/${id}`;
    const request = {};
    fetchDetails(url, request, dispatch);
    // eslint-disable-next-line
  }, [update]);

  const setEffort = () => {
    setEffortForm(true);
  };

  const setPriority = () => {
    setPriorityForm(true);
  };

  const refine = () => {
    setRefineForm(true);
  };

  const back = () => {
    resetCollection(dispatch);
    navigate("../projects/projectdetails/productbacklog", {
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
                  onClick={setEffort}
                  icon={<ClockCircleOutlined />}
                >
                  Set Time Estimate
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={setPriority}
                  icon={<OrderedListOutlined />}
                >
                  Set Priority
                </Button>
              </Col>
              <Col>
                <Button
                  type="primary"
                  onClick={refine}
                  icon={<ApartmentOutlined />}
                >
                  Refine
                </Button>
              </Col>
            </Row>
          );
        };

        const getStatus = () => {
          switch (details.status) {
            case "OPEN":
              return "default";
            case "IN_PROGRESS":
              return "processing";
            case "FINISHED":
              return "success";
            case "CANCELED":
              return "error";
            case "REFINED":
              return "warning";
            default:
              return "default";
          }
        };

        const getEffortForm = () => {
          if (effortForm) {
            return <TimeEstimateForm data={details} onClose={setEffortForm} />;
          }
        };

        const getPriorityForm = () => {
          if (priorityForm) {
            return <PriorityForm data={details} onClose={setPriorityForm} />;
          }
        };

        const getRefineForm = () => {
          if (refineForm) {
            return (
              <RefineForm
                data={details}
                onClose={setRefineForm}
                update={setUpdate}
              />
            );
          }
        };

        return (
          <>
            {getEffortForm()}
            {getPriorityForm()}
            {getRefineForm()}
            <Descriptions
              title={details.usTitle}
              bordered
              extra={allowedButtons()}
            >
              <Descriptions.Item label="Project">
                {details.projectID}
              </Descriptions.Item>
              <Descriptions.Item label="Title">
                {details.usTitle}
              </Descriptions.Item>
              <Descriptions.Item label="Priority">
                {details.priority}
              </Descriptions.Item>

              <Descriptions.Item label="Description" span={3}>
                {details.description}
              </Descriptions.Item>
              <Descriptions.Item label="Start Date">
                {details.usStartDate}
              </Descriptions.Item>
              <Descriptions.Item label="End Date" span={2}>
                {details.usEndDate}
              </Descriptions.Item>
              <Descriptions.Item label="Time Estimate">
                {details.timeEstimate}
              </Descriptions.Item>
              <Descriptions.Item label="Status" span={2}>
                <Badge status={getStatus()} text={details.status} />
              </Descriptions.Item>
              <Descriptions.Item label="Refined">
                {details.usRefined}
              </Descriptions.Item>

              <Descriptions.Item label="Parent User Story">
                {details.parentUserStory}
              </Descriptions.Item>
            </Descriptions>
          </>
        );
      }
    }
  }
}
