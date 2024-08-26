import axios from "axios";
import type {App} from "vue";

let config: Record<string, any> = {};

// 设置全局配置
const setConfig = (cfg?: Record<string, any>) => {
  config = {...config, ...cfg};
};

// 获取全局配置
const getConfig = (key?: string): any => {
  if (key) {
    return key.split(".").reduce((acc, curr) => acc && acc[curr], config);
  }
  return config;
};

/** 获取项目动态全局配置 */
export const getPlatformConfig = async (app: App): Promise<Record<string, any> | undefined> => {
  try {
    const {data} = await axios.get("./../../public/platform-config.json");
    console.log("获取项目动态全局配置", data);

    const $config = {...(app.config.globalProperties.$config || {}), ...data};
    app.config.globalProperties.$config = $config;

    setConfig($config);
    return $config;
  } catch (error) {
    console.error("无法获取平台配置，请在 public 文件夹下添加 platform-config.json 配置文件", error);
    throw error;
  }
};

export {getConfig, setConfig};
