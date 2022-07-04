import { useContext, useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import { fetchDetails } from "../context/Actions";
import EditForm from "../components/EditForm";
import PassForm from "../components/PassForm";

import { Spin, Descriptions, Badge, Button, Row, Col } from "antd";
import {
  EditOutlined,
  LockOutlined /*IdcardOutlined*/,
} from "@ant-design/icons";
import ProfilesForm from "../components/ProfilesForm";

export default function MyInfo() {
  const { state, dispatch } = useContext(AppContext);
  const { details } = state;
  const { loading, error, data } = details;
  const [editForm, setEditForm] = useState(false);
  const [passForm, setPassForm] = useState(false);
  const [profilesForm, setProfilesForm] = useState(false);
  const location = useLocation();

  const id = location.state.email;

  // GET REQUEST TO API
  useEffect(() => {
    const url = `${URL_API}/users/${id}`;
    const request = {};
    fetchDetails(url, request, dispatch);
    // eslint-disable-next-line
  }, []);

  const editDetails = () => {
    setEditForm(true);
  };

  const changePassword = () => {
    setPassForm(true);
  };

  // const changeProfiles = () => {
  //   setProfilesForm(true);
  // };

  //   const changeStatus = () => {
  //     console.log("change status");
  //   };

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
      if (data.length !== 0 && data[0].assignedIdProfiles !== undefined) {
        let details = data[0];

        let checkStatus = "error";
        let statusText = "Inactive";

        if (details.isActive === "True") {
          checkStatus = "success";
          statusText = "Active";
        }

        const profiles = details.assignedIdProfiles.map((profile, idx) => {
          if (idx === details.assignedIdProfiles.length - 1) {
            return profile;
          } else {
            return profile + ", ";
          }
        });

        // const userAdminPermissions = details.assignedIdProfiles.includes(
        //   "Admninistrator"
        // )
        //   ? true
        //   : false;

        const allowedButtons = () => {
          return (
            <Row gutter={8}>
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
                  onClick={changePassword}
                  icon={<LockOutlined />}
                >
                  Change Password
                </Button>
              </Col>
              {/* <Col hidden={userAdminPermissions}>
                <Button
                  type="primary"
                  onClick={changeProfiles}
                  icon={<IdcardOutlined />}
                >
                  Change Profiles
                </Button>
              </Col> */}
              {/* <Col hidden={permissions}>
                <Button type="primary" onClick={changeStatus}>
                  Change Status
                </Button>
              </Col> */}
            </Row>
          );
        };

        const getEditForm = () => {
          if (editForm) {
            return (
              <EditForm
                visible={editForm}
                onClose={setEditForm}
                data={details}
              />
            );
          }
        };

        const getPasswordForm = () => {
          if (passForm) {
            return (
              <PassForm
                visible={passForm}
                onClose={setPassForm}
                data={details}
              />
            );
          }
        };

        const getProfilesForm = () => {
          if (profilesForm) {
            return (
              <ProfilesForm
                visible={profilesForm}
                onClose={setProfilesForm}
                data={details}
              />
            );
          }
        };

        return (
          <>
            {getEditForm()}
            {getPasswordForm()}
            {getProfilesForm()}
            <Descriptions
              title={details.userName}
              bordered
              extra={allowedButtons()}
            >
              <Descriptions.Item label="Name">
                {details.userName}
              </Descriptions.Item>
              <Descriptions.Item label="Function">
                {details.function}
              </Descriptions.Item>
              <Descriptions.Item label="Status">
                <Badge status={checkStatus} text={statusText} />
              </Descriptions.Item>
              <Descriptions.Item label="E-mail" span={3}>
                {details.email}
              </Descriptions.Item>
              <Descriptions.Item label="Profiles">{profiles}</Descriptions.Item>
            </Descriptions>
          </>
        );
      }
    }
  }
}
