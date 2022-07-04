import { Button, Col, Drawer, Form, Input, Row, Space } from "antd";
import { useContext, useEffect, useState } from "react";

import { URL_API } from "../services/Service";
import { fetchDetails } from "../context/Actions";
import AppContext from "../context/AppContext";

const regularExpression = /^[a-zA-Z]*$/;

const EditForm = (props) => {
  const { dispatch } = useContext(AppContext);
  const [visible, setVisible] = useState(false);

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

  const onSubmit = (value) => {
    const body = {
      userName: value.name,
      function: value.function,
      photo: "",
      oldPassword: null,
      newPassword: null,
    };

    const url = `${URL_API}/users/${props.data.email}`;

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
  };

  return (
    <>
      <Drawer
        title="Edit account"
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
        <Form
          layout="vertical"
          onFinish={onSubmit}
          autoComplete="off"
          scrollToFirstError
          hideRequiredMark
        >
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="name"
                label="Name"
                initialValue={props.data.userName}
                rules={[
                  {
                    pattern: regularExpression,
                    message: "Only letters",
                  },
                  {
                    min: 2,
                    message: "At least two characters.",
                  },
                ]}
              >
                <Input placeholder="Please enter new name" />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="function"
                label="Function"
                initialValue={props.data.function}
                rules={[
                  {
                    pattern: regularExpression,
                    message: "Only letters",
                  },
                  {
                    min: 2,
                    message: "At least two characters.",
                  },
                  {
                    max: 20,
                    message: "Maximum of twenty characters.",
                  },
                ]}
              >
                <Input placeholder="Please enter new function" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="email"
                label="Email"
                initialValue={props.data.email}
              >
                <Input disabled />
              </Form.Item>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
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
            </Col>
          </Row>
        </Form>
      </Drawer>
    </>
  );
};

export default EditForm;
