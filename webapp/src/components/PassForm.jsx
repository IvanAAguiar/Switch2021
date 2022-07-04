import { useEffect, useState } from "react";
import { LockOutlined, QuestionCircleOutlined } from "@ant-design/icons";
import {
  Button,
  Row,
  Col,
  Drawer,
  Form,
  Input,
  Space,
  Popover,
  Typography,
  Spin,
} from "antd";
import { URL_API } from "../services/Service";
import { makeHTTPRequest } from "../services/Service";

const content = (
  <div>
    Minimum 7 characters
    <br />
    1 Uppercase
    <br />
    1 Lowercase
    <br />
    1 Number
    <br />
    1 Special character (ex. $, ? or @)
    <br />
  </div>
);

const regularExpression =
  /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,32}$/;

const PassForm = (props) => {
  const [visible, setVisible] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const [form] = Form.useForm();

  useEffect(() => {
    if (props.visible) {
      setVisible(true);
    }
    // eslint-disable-next-line
  }, []);

  const onClose = () => {
    setVisible(false);
    props.onClose(false);
  };

  const success = (res) => {
    setLoading(false);
    setVisible(false);
    props.onClose(false);
  };

  const failure = (err) => {
    setLoading(false);
    setError(err);
  };

  const onSubmit = (value) => {
    setLoading(true);

    const body = {
      userName: "",
      function: "",
      photo: "",
      oldPassword: value.actual,
      newPassword: value.password,
    };

    const url = `${URL_API}/users/${props.data.email}`;

    const request = {
      method: "PATCH",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };

    makeHTTPRequest(url, request, success, failure);
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

  if (loading === true) {
    return (
      <div className="spin-loading">
        <Spin />
      </div>
    );
  } else {
    return (
      <>
        <Drawer
          title="Change Password"
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
          {getError()}
          <Form
            layout="vertical"
            form={form}
            name="register"
            onFinish={onSubmit}
            autoComplete="off"
            scrollToFirstError
          >
            <Form.Item
              name="actual"
              label="Actual Password"
              rules={[
                {
                  required: true,
                  message: "Please input your password!",
                },
                {
                  pattern: regularExpression,
                  message: "See password rules in the question mark beside ",
                },
              ]}
            >
              <Row gutter={8} align="middle">
                <Col span={23}>
                  <Input.Password
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    placeholder="Actual Password"
                  />
                </Col>
                <Col span={1}>
                  <Popover title="Password Rules:" content={content}>
                    <QuestionCircleOutlined style={{ fontSize: 20 }} />
                  </Popover>
                </Col>
              </Row>
            </Form.Item>
            <Form.Item
              name="password"
              label="New Password"
              rules={[
                {
                  required: true,
                  message: "Please input your password!",
                },
                {
                  pattern: regularExpression,
                  message: "See password rules in the question mark beside ",
                },
              ]}
              hasFeedback
            >
              <Row gutter={8} align="middle">
                <Col span={23}>
                  <Input.Password
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    placeholder="New Password"
                  />
                </Col>
                <Col span={1}>
                  <Popover title="Password Rules:" content={content}>
                    <QuestionCircleOutlined style={{ fontSize: 20 }} />
                  </Popover>
                </Col>
              </Row>
            </Form.Item>

            <Form.Item
              name="confirm"
              label="Confirm Password"
              dependencies={["password"]}
              hasFeedback
              rules={[
                {
                  required: true,
                  message: "Please confirm your password!",
                },
                {
                  pattern: regularExpression,
                  message: "See password rules in the question mark beside ",
                },

                ({ getFieldValue }) => ({
                  validator(_, value) {
                    if (!value || getFieldValue("password") === value) {
                      return Promise.resolve();
                    }

                    return Promise.reject(
                      new Error(
                        "The two passwords that you entered do not match!"
                      )
                    );
                  },
                }),
              ]}
            >
              <Row gutter={8} align="middle">
                <Col span={23}>
                  <Input.Password
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    placeholder="Confirm New Password"
                  />
                </Col>
                <Col span={1}>
                  <Popover title="Password Rules:" content={content}>
                    <QuestionCircleOutlined style={{ fontSize: 20 }} />
                  </Popover>
                </Col>
              </Row>
            </Form.Item>
            <Form.Item
              wrapperCol={{
                offset: 11,
                span: 16,
              }}
            >
              <Button type="primary" htmlType="submit">
                Submit
              </Button>
            </Form.Item>
          </Form>
        </Drawer>
      </>
    );
  }
};

export default PassForm;
