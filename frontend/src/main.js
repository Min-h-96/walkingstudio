 /*eslint-disable*/
import Vue from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import Managing from "./components";
import router from './router';
Vue.config.productionTip = false;
require('./GlobalStyle.css');

const axios = require("axios").default;

// backend host url
axios.backend = null; //"http://localhost:8088";

// axios.backendUrl = new URL(axios.backend);
axios.fixUrl = function(original){

  if(!axios.backend && original.indexOf("/")==0) return original;

  var url = null;

  try{
    url = new URL(original);
  }catch(e){
    url = new URL(axios.backend + original);
  }

  if(!axios.backend) return url.pathname;

  url.hostname = axios.backendUrl.hostname;
  url.port = axios.backendUrl.port;

  return url.href;
}

const templateFiles = require.context("./components", true);
Vue.prototype.$ManagerLists = [];
templateFiles.keys().forEach(function(tempFiles) {
  if (!tempFiles.includes("Manager.vue") && tempFiles.includes("vue")) {
    Vue.prototype.$ManagerLists.push(
      tempFiles.replace("./", "").replace(".vue", "")
    );
  }
});
Vue.use(Managing);
const pluralCaseList = []

pluralCaseList.push( {plural: "walkinghistories/walkingHsts", pascal: "WalkinghistoryWalkingHst"} )

pluralCaseList.push( {plural: "weathers/usrtFcstHsts", pascal: "WeatherUsrtFcstHst"} )
pluralCaseList.push( {plural: "weathers/weatherStnInfos", pascal: "WeatherWeatherStnInfo"} )

pluralCaseList.push( {plural: "users/users", pascal: "UserUser"} )

pluralCaseList.push( {plural: "userwalkingstats/walkingStatByUsers", pascal: "UserwalkingstatWalkingStatByUser"} )

pluralCaseList.push( {plural: "teamwalkingstats/walkingStatByTeams", pascal: "TeamwalkingstatWalkingStatByTeam"} )

pluralCaseList.push( {plural: "companywalkingstats/walkingStatByCompanies", pascal: "CompanywalkingstatWalkingStatByCompany"} )

pluralCaseList.push( {plural: "challenges/challengeInfos", pascal: "ChallengeChallengeInfo"} )
pluralCaseList.push( {plural: "challenges/challengeHsts", pascal: "ChallengeChallengeHst"} )

pluralCaseList.push( {plural: "calculatepoints/pointStandardInfos", pascal: "CalculatepointPointStandardInfo"} )


Vue.prototype.$ManagerLists.forEach(function(item, idx) {
  pluralCaseList.forEach(function(tmp) {
    if(item.toLowerCase() == tmp.pascal.toLowerCase()) {
      var obj = {
        name: item,
        plural: tmp.plural
      }
      Vue.prototype.$ManagerLists[idx] = obj
    }
  })
})

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount("#app");
