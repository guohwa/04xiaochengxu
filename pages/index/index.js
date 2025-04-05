Page({
  data: {
    // 不再需要welcomeText
  },
  onLoad() {
    console.log('首页加载完成')
  },
  
  // 导航到出生日期输入页面
  navigateToBirthdateInput() {
    wx.navigateTo({
      url: '/pages/birthdate-input/birthdate-input'
    })
  }
})