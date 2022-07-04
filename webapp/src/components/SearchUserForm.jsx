import { useState, useEffect } from "react";
import { Select } from "antd";

import { URL_API } from "../services/Service";
import { makeHTTPRequest } from "../services/Service";

const { Option } = Select;

const SearchUserForm = (props) => {
  const [data, setData] = useState([]);

  const onChange = (value) => {
    props.selected(value);
  };

  useEffect(() => {
    const url = `${URL_API}/users`;

    const request = {};

    makeHTTPRequest(url, request, success, failure);

    // eslint-disable-next-line
  }, []);

  const success = (res) => {
    const users = res._embedded.Users.map((user, idx) => {
      return (
        <Option key={idx} value={user.email}>
          {`${user.userName}   (${user.email})`}
        </Option>
      );
    });

    setData(users);
  };

  const failure = (err) => {
    console.log("error");
  };

  return (
    <Select
      showSearch
      placeholder="Select a person"
      optionFilterProp="children"
      onChange={onChange}
      filterOption={(input, option) =>
        option.children.toLowerCase().includes(input.toLowerCase())
      }
    >
      {data}
    </Select>
  );
};

export default SearchUserForm;
