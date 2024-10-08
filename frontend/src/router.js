
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import WalkingHistoryWalkingHstManager from "./components/listers/WalkingHistoryWalkingHstCards"
import WalkingHistoryWalkingHstDetail from "./components/listers/WalkingHistoryWalkingHstDetail"

import WeatherUsrtFcstHstManager from "./components/listers/WeatherUsrtFcstHstCards"
import WeatherUsrtFcstHstDetail from "./components/listers/WeatherUsrtFcstHstDetail"
import WeatherWeatherStnInfoManager from "./components/listers/WeatherWeatherStnInfoCards"
import WeatherWeatherStnInfoDetail from "./components/listers/WeatherWeatherStnInfoDetail"

import UserUserManager from "./components/listers/UserUserCards"
import UserUserDetail from "./components/listers/UserUserDetail"

import UserWalkingStatWalkingStatByUserManager from "./components/listers/UserWalkingStatWalkingStatByUserCards"
import UserWalkingStatWalkingStatByUserDetail from "./components/listers/UserWalkingStatWalkingStatByUserDetail"

import TeamWalkingStatWalkingStatByTeamManager from "./components/listers/TeamWalkingStatWalkingStatByTeamCards"
import TeamWalkingStatWalkingStatByTeamDetail from "./components/listers/TeamWalkingStatWalkingStatByTeamDetail"

import CompanyWalkingStatWalkingStatByCompanyManager from "./components/listers/CompanyWalkingStatWalkingStatByCompanyCards"
import CompanyWalkingStatWalkingStatByCompanyDetail from "./components/listers/CompanyWalkingStatWalkingStatByCompanyDetail"

import ChallengeChallengeInfoManager from "./components/listers/ChallengeChallengeInfoCards"
import ChallengeChallengeInfoDetail from "./components/listers/ChallengeChallengeInfoDetail"
import ChallengeChallengeHstManager from "./components/listers/ChallengeChallengeHstCards"
import ChallengeChallengeHstDetail from "./components/listers/ChallengeChallengeHstDetail"

import CalculatePointPointStandardInfoManager from "./components/listers/CalculatePointPointStandardInfoCards"
import CalculatePointPointStandardInfoDetail from "./components/listers/CalculatePointPointStandardInfoDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/walkingHistories/walkingHsts',
                name: 'WalkingHistoryWalkingHstManager',
                component: WalkingHistoryWalkingHstManager
            },
            {
                path: '/walkingHistories/walkingHsts/:id',
                name: 'WalkingHistoryWalkingHstDetail',
                component: WalkingHistoryWalkingHstDetail
            },

            {
                path: '/weathers/usrtFcstHsts',
                name: 'WeatherUsrtFcstHstManager',
                component: WeatherUsrtFcstHstManager
            },
            {
                path: '/weathers/usrtFcstHsts/:id',
                name: 'WeatherUsrtFcstHstDetail',
                component: WeatherUsrtFcstHstDetail
            },
            {
                path: '/weathers/weatherStnInfos',
                name: 'WeatherWeatherStnInfoManager',
                component: WeatherWeatherStnInfoManager
            },
            {
                path: '/weathers/weatherStnInfos/:id',
                name: 'WeatherWeatherStnInfoDetail',
                component: WeatherWeatherStnInfoDetail
            },

            {
                path: '/users/users',
                name: 'UserUserManager',
                component: UserUserManager
            },
            {
                path: '/users/users/:id',
                name: 'UserUserDetail',
                component: UserUserDetail
            },

            {
                path: '/userWalkingStats/walkingStatByUsers',
                name: 'UserWalkingStatWalkingStatByUserManager',
                component: UserWalkingStatWalkingStatByUserManager
            },
            {
                path: '/userWalkingStats/walkingStatByUsers/:id',
                name: 'UserWalkingStatWalkingStatByUserDetail',
                component: UserWalkingStatWalkingStatByUserDetail
            },

            {
                path: '/teamWalkingStats/walkingStatByTeams',
                name: 'TeamWalkingStatWalkingStatByTeamManager',
                component: TeamWalkingStatWalkingStatByTeamManager
            },
            {
                path: '/teamWalkingStats/walkingStatByTeams/:id',
                name: 'TeamWalkingStatWalkingStatByTeamDetail',
                component: TeamWalkingStatWalkingStatByTeamDetail
            },

            {
                path: '/companyWalkingStats/walkingStatByCompanies',
                name: 'CompanyWalkingStatWalkingStatByCompanyManager',
                component: CompanyWalkingStatWalkingStatByCompanyManager
            },
            {
                path: '/companyWalkingStats/walkingStatByCompanies/:id',
                name: 'CompanyWalkingStatWalkingStatByCompanyDetail',
                component: CompanyWalkingStatWalkingStatByCompanyDetail
            },

            {
                path: '/challenges/challengeInfos',
                name: 'ChallengeChallengeInfoManager',
                component: ChallengeChallengeInfoManager
            },
            {
                path: '/challenges/challengeInfos/:id',
                name: 'ChallengeChallengeInfoDetail',
                component: ChallengeChallengeInfoDetail
            },
            {
                path: '/challenges/challengeHsts',
                name: 'ChallengeChallengeHstManager',
                component: ChallengeChallengeHstManager
            },
            {
                path: '/challenges/challengeHsts/:id',
                name: 'ChallengeChallengeHstDetail',
                component: ChallengeChallengeHstDetail
            },

            {
                path: '/calculatePoints/pointStandardInfos',
                name: 'CalculatePointPointStandardInfoManager',
                component: CalculatePointPointStandardInfoManager
            },
            {
                path: '/calculatePoints/pointStandardInfos/:id',
                name: 'CalculatePointPointStandardInfoDetail',
                component: CalculatePointPointStandardInfoDetail
            },



    ]
})
