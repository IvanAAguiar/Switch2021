import {
  Button,
  Drawer,
  Space,
  Transfer,
  Typography,
  Spin,
  Row,
  Col,
} from "antd";
import { useState, useEffect, useContext } from "react";
import AppContext from "../context/AppContext";
import { makeHTTPRequest } from "../services/Service";
import { URL_API } from "../services/Service";
import { fetchDetails } from "../context/Actions";

const ProfilesForm = (props) => {
  const { dispatch } = useContext(AppContext);
  const [visible, setVisible] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [targetKeys, setTargetKeys] = useState([]);
  const [selectedKeys, setSelectedKeys] = useState([]);
  const [mockData, setMockData] = useState([]);

  useEffect(() => {
    if (props.visible) {
      setVisible(true);
      setLoading(true);
    }

    const url = `${URL_API}/profiles`;

    const request = {};

    makeHTTPRequest(url, request, success, failure);
    // eslint-disable-next-line
  }, []);

  const success = (res) => {
    setLoading(false);

    const data = res.internalUserProfiles.content.map((profile, key) => {
      return {
        key: key,
        title: profile.userProfileName,
      };
    });

    setMockData(data);
  };

  const failure = (err) => {
    setLoading(false);
    setError(err);
  };

  useEffect(() => {
    const initialTargetKeys = mockData
      .filter((item) => props.data.assignedIdProfiles.includes(item.title))
      .map((item) => item.key);

    setTargetKeys(initialTargetKeys);
    // eslint-disable-next-line
  }, [mockData]);

  const onClose = () => {
    setVisible(false);
    props.onClose(false);
  };

  const onChange = (nextTargetKeys, direction, moveKeys) => {
    setTargetKeys(nextTargetKeys);
  };

  const onSelectChange = (sourceSelectedKeys, targetSelectedKeys) => {
    setSelectedKeys([...sourceSelectedKeys, ...targetSelectedKeys]);
  };

  const handleSubmit = () => {
    if (targetKeys.length !== 0) {
      let profiles = targetKeys.map((key) => {
        return mockData[key].title;
      });

      const body = {
        profilesId: profiles,
      };

      const url = `${URL_API}/users/${props.data.email}/assignProfiles`;

      const putRequest = {
        method: "PATCH",
        headers: {
          "content-Type": "application/json",
        },
        body: JSON.stringify(body),
      };

      fetchDetails(url, putRequest, dispatch);
      setVisible(false);
      props.onClose(false);
    }

    setError("User must have at least one profile");
  };

  const getError = () => {
    if (error !== "") {
      return (
        <Typography.Text
          style={{
            marginBottom: "10px",
            display: "flex",
            justifyContent: "center",
            color: "#f5222d",
          }}
        >
          {JSON.stringify(error)}
        </Typography.Text>
      );
    }
  };

  const getLoading = () => {
    if (loading === true) {
      return (
        <div className="spin-loading">
          <Spin />
        </div>
      );
    }
  };

  return (
    <>
      <Drawer
        title="Change Profiles"
        width={720}
        onClose={onClose}
        visible={visible}
        bodyStyle={{
          paddingBottom: 80,
        }}
        extra={
          <Space>
            <Button onClick={onClose}>Cancel</Button>
          </Space>
        }
      >
        {getLoading()}
        {getError()}
        <Row gutter={[0, 16]}>
          <Col push="5" span={24}>
            <Transfer
              dataSource={mockData}
              titles={["Profiles", "Actual"]}
              targetKeys={targetKeys}
              selectedKeys={selectedKeys}
              onChange={onChange}
              onSelectChange={onSelectChange}
              render={(item) => item.title}
            />
          </Col>
          <Col push="11" span={24}>
            <Button type="primary" onClick={handleSubmit}>
              Submit
            </Button>
          </Col>
        </Row>
      </Drawer>
    </>
  );
};

export default ProfilesForm;
