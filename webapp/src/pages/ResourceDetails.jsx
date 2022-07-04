import { useContext, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { fetchDetails, resetCollection } from "../context/Actions";
import RoleForm from "../components/RoleForm";

import { Spin, Descriptions, Button, Row, Col } from "antd";
import { EditOutlined, CaretLeftOutlined } from "@ant-design/icons";

const rex = /([A-Z])([A-Z])([a-z])|([a-z])([A-Z])/g;

export default function ResourceDetails() {
  const { state, dispatch } = useContext(AppContext);
  const { details } = state;
  const { loading, error, data } = details;
  const [roleForm, setRoleForm] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();

  const id =
    location.state.user +
    "&" +
    location.state.project +
    "&" +
    location.state.start;

  // GET REQUEST TO API
  useEffect(() => {
    const url = `${URL_API}/resources/${id}`;
    const request = {};
    fetchDetails(url, request, dispatch);
    // eslint-disable-next-line
  }, []);

  const changeRole = () => {
    setRoleForm(true);
  };

  const back = () => {
    resetCollection(dispatch);
    navigate("../projects/projectdetails/projectteam", {
      state: {
        endDate: location.state.endDateProject,
        project: location.state.project,
      },
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
      if (data.length !== 0) {
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
                  onClick={changeRole}
                  icon={<EditOutlined />}
                >
                  Change Role
                </Button>
              </Col>
            </Row>
          );
        };

        const getRoleForm = () => {
          if (roleForm) {
            return (
              <RoleForm
                visible={roleForm}
                onClose={setRoleForm}
                data={details}
                endDate={location.state.endDateProject}
                id={id}
              />
            );
          }
        };

        return (
          <>
            {getRoleForm()}

            <Descriptions
              title="Resource Details"
              bordered
              extra={allowedButtons()}
            >
              <Descriptions.Item label="Project">
                {details.project}
              </Descriptions.Item>
              <Descriptions.Item label="User" span={2}>
                {details.user}
              </Descriptions.Item>

              <Descriptions.Item label="Start Date">
                {details.startDate}
              </Descriptions.Item>
              <Descriptions.Item label="End Date" span={2}>
                {details.endDate}
              </Descriptions.Item>
              <Descriptions.Item label="Role">
                {details.role.replace(rex, "$1$4 $2$3$5")}
              </Descriptions.Item>
              <Descriptions.Item label="Allocation">
                {details.allocation * 100 + " %"}
              </Descriptions.Item>
              <Descriptions.Item label="Cost">
                {details.cost + " €/hour"}
              </Descriptions.Item>
            </Descriptions>
          </>
        );
      }
    }
  }
}
