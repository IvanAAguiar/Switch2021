import {
  Button,
  Col,
  Drawer,
  Form,
  Input,
  DatePicker,
  Row,
  Space,
  Spin,
} from "antd";
import { useContext, useState } from "react";
import { fetchDetails, resetDetails } from "../context/Actions";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import moment from "moment";

const dateFormat = "YYYY-MM-DD";

const StartSprintForm = (props) => {
  const { dispatch } = useContext(AppContext);
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const onClose = () => {
    props.onClose(false);
  };

  const onSubmit = (value) => {
    setLoading(true);

    const body = {
      startDate: moment(value.startDate).format(dateFormat),
    };

    const url = `${URL_API}/sprints/${props.data.sprintID}`;

    const request = {
      method: "PATCH",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };

    fetchDetails(url, request, dispatch);

    resetDetails(dispatch);
    props.update(true);
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
        title="Start a Sprint"
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
                name="sprint"
                label="Sprint"
                initialValue={props.data.sprintID}
              >
                <Input disabled />
              </Form.Item>
            </Col>

            <Col span={24}>
              <Form.Item
                name="startDate"
                label="Start Date"
                rules={[{ required: true, message: "Please enter name" }]}
              >
                <DatePicker format={dateFormat} />
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

export default StartSprintForm;
