import {
  Button,
  Col,
  DatePicker,
  Drawer,
  Form,
  Input,
  InputNumber,
  Row,
  Select,
  Space,
  Typography,
  Spin,
  Slider,
} from "antd";
import { useState } from "react";

import { makeHTTPRequest, URL_API } from "../services/Service";

import moment from "moment";
import SearchUserForm from "./SearchUserForm";

const { Option } = Select;
const { RangePicker } = DatePicker;

const dateFormat = "YYYY-MM-DD";

const roles = [
  "Project Manager",
  "Product Owner",
  "Scrum Master",
  "Team Member",
];

const CreateResourceForm = (props) => {
  //   const { dispatch } = useContext(AppContext);
  //   const [visible, setVisible] = useState(false);
  const [form] = Form.useForm();
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  //   const [data, setData] = useState([]);

  //   useEffect(() => {
  //     // if (props.visible) {
  //     //   setVisible(true);
  //       //   setLoading(true);
  //     }

  //     //   const urlTypologies = `${URL_API}/typologies`;

  //     //   const request = {};

  //     //   makeHTTPRequest(urlTypologies, request, success, failure);

  //     // eslint-disable-next-line
  //   }, []);

  // const success = (res) => {
  //   setLoading(false);

  //   const typologies = res._embedded.Typologies.map((typo, idx) => {
  //     return (
  //       <Option key={idx} value={typo.description}>
  //         {typo.description}
  //       </Option>
  //     );
  //   });

  //   setData(typologies);
  // };

  // const failure = (err) => {
  //   setLoading(false);
  //   setError(err);
  // };

  const onClose = () => {
    props.onClose(false);
  };

  const rolesOptions = () =>
    roles.map((role, idx) => {
      return (
        <Option key={idx} value={role}>
          {role}
        </Option>
      );
    });

  const success = (res) => {
    setLoading(false);
    props.update(true);
    props.onClose(false);
  };

  const failure = (err) => {
    setLoading(false);
    setError(err);
  };

  const onSubmit = (value) => {
    setLoading(true);

    const role = value.role.replace(/\s/g, "");

    const allocation = value.allocation / 100;

    const body = {
      systemUserID: value.user,
      projectId: props.data.code,
      projectRole: role,
      startDate: moment(value.dates[0]).format(dateFormat),
      endDate: moment(value.dates[1]).format(dateFormat),
      costPerHour: value.cost,
      percentageOfAllocation: allocation,
    };

    const url = `${URL_API}/resources`;

    const request = {
      method: "POST",
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

  const getLoading = () => {
    if (loading === true) {
      return (
        <div className="spin-loading">
          <Spin />
        </div>
      );
    }
  };

  const userSelected = (user) => {
    form.setFieldsValue({
      user: user,
    });
  };

  return (
    <>
      <Drawer
        title="Create new resource"
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
        {getError()}
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
                name="user"
                label="User"
                rules={[
                  {
                    required: true,
                    message: "Please enter user name or email",
                  },
                ]}
              >
                <SearchUserForm selected={userSelected} />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="project"
                label="Project"
                initialValue={props.data.projectName}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item name="role" label="Role" initialValue="Team Member">
                <Select>{rolesOptions()}</Select>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="cost"
                label="Cost Per Hour"
                rules={[{ required: true, message: "Please enter cost" }]}
              >
                <InputNumber min={0} addonAfter="€/hour" />
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item
                name="allocation"
                label="Percentage of Allocation (%)"
                initialValue={10}
                rules={[{ required: true, message: "Please enter allocation" }]}
              >
                <Slider
                  min={10}
                  marks={{
                    10: "10%",
                    25: "25%",
                    50: "Half",
                    75: "75%",
                    100: "Full",
                  }}
                />
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item
                name="dates"
                label={`Start Date \xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0 End Date`}
                initialValue={[
                  moment(new Date(), dateFormat),
                  moment(props.data.endDate, dateFormat),
                ]}
                rules={[
                  {
                    required: true,
                    message: "Please enter start and end date",
                  },
                ]}
              >
                <RangePicker format={dateFormat} />
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

export default CreateResourceForm;
