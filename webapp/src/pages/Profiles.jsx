import { useContext, useEffect, useState } from "react";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import { fetchCollections, resetCollection } from "../context/Actions";

import { Col, Space, Row, Spin, Button } from "antd";
import Search from "antd/lib/input/Search";
import { PlusOutlined } from "@ant-design/icons";

import TableCollections from "../components/TableCollections";
import CreateProfileForm from "../components/CreateProfileForm";

export default function Profiles() {
  const { state, dispatch } = useContext(AppContext);
  const { collection } = state;
  const { loading, error, data } = collection;

  const [search, setSearch] = useState("");
  const [externalSearch, setExternalSearch] = useState("");
  const [profileForm, setProfileForm] = useState(false);

  const handleButtonClickDelete = () => {
    console.log("Deleted");
  };

  const columns = [
    {
      title: "ID",
      dataIndex: "key",

      sorter: (a, b) => ("" + a.key).localeCompare(b.key),
    },
    {
      title: "Name",
      dataIndex: "name",
      sorter: (a, b) => ("" + a.name).localeCompare(b.name),
    },
    {
      title: "Action",
      key: "action",
      render: (_, record) => (
        <Space size="middle">
          <button
            className="anchor-button"
            onClick={() => handleButtonClickDelete(record)}
          >
            Delete
          </button>
        </Space>
      ),
    },
  ];

  const columnsExt = [
    {
      title: "ID",
      dataIndex: "key",

      sorter: (a, b) => ("" + a.key).localeCompare(b.key),
    },
    {
      title: "Name",
      dataIndex: "name",
      sorter: (a, b) => ("" + a.name).localeCompare(b.name),
    },
  ];

  useEffect(() => {
    resetCollection(dispatch);
    let url = `${URL_API}/profiles`;

    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, [profileForm]);

  const onSearch = (value) => {
    setSearch(value);
  };

  const onSearchExternal = (value) => {
    setExternalSearch(value);
  };

  const searchProfiles = () => {
    return (
      <Search
        style={{
          width: 300,
          marginBottom: 10,
        }}
        placeholder="Search for Profile Here"
        onSearch={onSearch}
        enterButton
      />
    );
  };

  const searchProfilesExternal = () => {
    return (
      <Search
        style={{
          width: 300,
          marginBottom: 10,
        }}
        placeholder="Search for Profile Here"
        onSearch={onSearchExternal}
        enterButton
      />
    );
  };

  const createProfile = () => {
    setProfileForm(true);
  };

  const getCreateProfileForm = () => {
    if (profileForm) {
      return (
        <CreateProfileForm visible={profileForm} onClose={setProfileForm} />
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
    if (error !== null) {
      return <h1 style={{ color: "red" }}>{error}</h1>;
    } else {
      if (data[0].internalUserProfiles !== undefined) {
        let internal = data[0].internalUserProfiles.content.map(
          (profile, idx) => {
            return { key: idx, name: profile.userProfileName };
          }
        );
        let external = data[0].externalUserProfiles.content.map(
          (profile, idx) => {
            return { key: idx, name: profile.userProfileName };
          }
        );

        if (search !== undefined) {
          internal = internal.filter((data) => {
            const toCheckName = JSON.stringify(data.name).toLowerCase().trim();

            const searchProfiles = search.toLowerCase().trim();
            return toCheckName.includes(searchProfiles);
          });
        }

        if (externalSearch !== undefined) {
          external = external.filter((data) => {
            const toCheckName = JSON.stringify(data.name).toLowerCase().trim();

            const searchProfiles = externalSearch.toLowerCase().trim();
            return toCheckName.includes(searchProfiles);
          });
        }

        return (
          <>
            {getCreateProfileForm()}
            <Row gutter={16}>
              <Col span={2}>
                <Button
                  type="primary"
                  onClick={createProfile}
                  icon={<PlusOutlined />}
                >
                  Create
                </Button>
              </Col>
              <Col span={10}>{searchProfiles()}</Col>

              <Col span={12}>{searchProfilesExternal()}</Col>
            </Row>
            <Row gutter={16}>
              <Col span={12}>
                <TableCollections
                  title="Internal"
                  data={internal}
                  header={columns}
                />
              </Col>
              <Col span={12}>
                <TableCollections
                  title="External"
                  data={external}
                  header={columnsExt}
                />
              </Col>
            </Row>
          </>
        );
      }
    }
  }
}
