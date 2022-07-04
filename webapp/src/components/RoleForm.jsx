import {
  Button,
  Drawer,
  Space,
  Form,
  Select,
  Typography,
  Spin,
  Row,
  Col,
  Input,
  DatePicker,
  InputNumber,
  Slider,
} from "antd";
import { useState, useEffect /*useContext*/ } from "react";
// import AppContext from "../context/AppContext";
import { makeHTTPRequest } from "../services/Service";
import { URL_API } from "../services/Service";
import moment from "moment";

const { Option } = Select;
const { RangePicker } = DatePicker;

const dateFormat = "YYYY-MM-DD";

const rex = /([A-Z])([A-Z])([a-z])|([a-z])([A-Z])/g;

const RoleForm = (props) => {
  // const { dispatch } = useContext(AppContext);
  const [visible, setVisible] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [mockData, setMockData] = useState([]);

  useEffect(() => {
    if (props.visible) {
      setVisible(true);
      setLoading(true);
    }

    const url = `${URL_API}/resources/roles`;

    const request = {};

    makeHTTPRequest(url, request, success, failure);
    // eslint-disable-next-line
  }, []);

  const success = (res) => {
    setLoading(false);

    const data = res._embedded.outputProjectRoleDTOList.map((role, idx) => {
      return (
        <Option key={idx} value={role.role}>
          {role.role.replace(rex, "$1$4 $2$3$5")}
        </Option>
      );
    });

    setMockData(data);
  };

  const failure = (err) => {
    setLoading(false);
    setError(err);
  };

  const onSubmit = (value) => {
    setLoading(true);

    const role = value.new.replace(/\s/g, "");

    const allocation = value.allocation / 100;

    const body = {
      role: role,
      startDate: moment(value.dates[0]).format(dateFormat),
      endDate: moment(value.dates[1]).format(dateFormat),
      costPerHour: value.cost,
      percentageOfAllocation: allocation,
    };

    const url = `${URL_API}/resources/${props.id}`;

    const request = {
      method: "PATCH",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };

    makeHTTPRequest(url, request, success, failure);
  };

  const onClose = () => {
    setVisible(false);
    props.onClose(false);
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
        title="Change Role"
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
                name="actual"
                label="Actual Role"
                initialValue={props.data.role}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item name="new" label="New Role">
                <Select>{mockData}</Select>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="cost"
                label="Cost Per Hour"
                initialValue={props.data.cost}
                rules={[{ required: true, message: "Please enter cost" }]}
              >
                <InputNumber min={0} addonAfter="€/hour" />
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item
                name="allocation"
                label="Percentage of Allocation (%)"
                initialValue={props.data.allocation * 100}
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
                  moment(props.endDate, dateFormat),
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
                  offset: 9,
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

export default RoleForm;
