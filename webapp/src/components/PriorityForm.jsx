import {
  Button,
  Col,
  Drawer,
  Form,
  Input,
  Row,
  Space,
  Spin,
  Radio,
} from "antd";
import { useContext, useState } from "react";
import { fetchDetails } from "../context/Actions";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";

const PriorityForm = (props) => {
  const { dispatch } = useContext(AppContext);
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const onClose = () => {
    props.onClose(false);
  };

  const onSubmit = (value) => {
    setLoading(true);

    const body = {
      priority: value.priority,
      timeEstimate: 0,
    };

    const url = `${URL_API}/userstories/${props.data.id}/effort`;

    const request = {
      method: "PATCH",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };

    fetchDetails(url, request, dispatch);

    props.onClose(false);
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
        title="Define Time Estimate"
        width={720}
        onClose={onClose}
        visible="true"
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
        <Form
          form={form}
          layout="vertical"
          onFinish={onSubmit}
          autoComplete="off"
          scrollToFirstError
        >
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="project"
                label="Project"
                initialValue={props.data.projectID}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item
                name="userStory"
                label="User Story"
                initialValue={props.data.usTitle}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item
                name="priority"
                label="Priority"
                initialValue={props.data.priority}
                rules={[{ required: true, message: "Please enter priority" }]}
              >
                <Radio.Group>
                  <Radio value={1}>1 (Higher)</Radio>
                  <Radio value={2}>2</Radio>
                  <Radio value={3}>3</Radio>
                  <Radio value={4}>4</Radio>
                  <Radio value={5}>5 (Lower)</Radio>
                </Radio.Group>
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

export default PriorityForm;
