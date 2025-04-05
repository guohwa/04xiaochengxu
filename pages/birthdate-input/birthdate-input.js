Page({
  data: {
    solarDate: '',
    selectedHour: '',
    currentDate: '',
    hours: ['子时', '丑时', '寅时', '卯时', '辰时', '巳时', '午时', '未时', '申时', '酉时', '戌时', '亥时']
  },

  onLoad: function() {
    // 设置当前日期作为日期选择器的最大值
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    const currentDate = `${year}-${month}-${day}`;
    
    this.setData({
      currentDate: currentDate,
      solarDate: currentDate // 设置当前日期为默认值
    });
  },

  bindDateChange: function(e) {
    this.setData({
      solarDate: e.detail.value
    });
  },

  bindHourChange: function(e) {
    const index = e.detail.value;
    this.setData({
      selectedHour: this.data.hours[index]
    });
  },

  convertToLunar: function() {
    wx.request({
      url: 'https://express-nq0n-152130-4-1352756383.sh.run.tcloudbase.com/api/lunar', // 微信云托管地址
      method: 'POST',
      data: {
        date: this.data.solarDate,
        hour: this.data.selectedHour
      },
      success: (res) => {
        wx.navigateTo({
          url: '/pages/result/result?lunar=' + res.data.lunar
        });
      },
      fail: (err) => {
        console.error('API请求失败:', err);
        wx.showToast({
          title: '服务器连接失败',
          icon: 'none',
          duration: 2000
        });
      }
    });
  }
})