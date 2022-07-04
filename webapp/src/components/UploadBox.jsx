import { UploadOutlined } from "@ant-design/icons";
import { Button, message, Upload } from "antd";
const props = {
  beforeUpload: (file) => {
    const isValidFormat =
      file.type === "image/png" || file.type === "image/jpeg";

    if (!isValidFormat) {
      message.error(`${file.name} is not a png file`);
    }

    return isValidFormat || Upload.LIST_IGNORE;
  },
  onChange: (info) => {
    console.log(info.fileList);
  },
};

const UploadButton = () => (
  <Upload {...props}>
    <Button icon={<UploadOutlined />}>Upload png/jpeg only</Button>
  </Upload>
);

export default UploadButton;
