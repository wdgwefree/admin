const GlobalConstantsPlugin = {

  install(app) {
    // 设置全局常量
    app.config.globalProperties.$code={
      "success":0
    };
  }
};

export default GlobalConstantsPlugin;