Page({
  data: {
    lunarDate: ''
  },

  onLoad: function(options) {
    if (options.lunar) {
      this.setData({
        lunarDate: options.lunar
      });
    }
  },

  goBack: function() {
    wx.navigateBack();
  },

  goHome: function() {
    wx.switchTab({
      url: '/pages/index/index'
    });
  }
})