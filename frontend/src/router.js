
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import WalkinghistoryWalkingHstManager from "./components/listers/WalkinghistoryWalkingHstCards"
import WalkinghistoryWalkingHstDetail from "./components/listers/WalkinghistoryWalkingHstDetail"

import WeatherUsrtFcstHstManager from "./components/listers/WeatherUsrtFcstHstCards"
import WeatherUsrtFcstHstDetail from "./components/listers/WeatherUsrtFcstHstDetail"
import WeatherWeatherStnInfoManager from "./components/listers/WeatherWeatherStnInfoCards"
import WeatherWeatherStnInfoDetail from "./components/listers/WeatherWeatherStnInfoDetail"

import UserUserManager from "./components/listers/UserUserCards"
import UserUserDetail from "./components/listers/UserUserDetail"

import UserwalkingstatWalkingStatByUserManager from "./components/listers/UserwalkingstatWalkingStatByUserCards"
import UserwalkingstatWalkingStatByUserDetail from "./components/listers/UserwalkingstatWalkingStatByUserDetail"

import TeamwalkingstatWalkingStatByTeamManager from "./components/listers/TeamwalkingstatWalkingStatByTeamCards"
import TeamwalkingstatWalkingStatByTeamDetail from "./components/listers/TeamwalkingstatWalkingStatByTeamDetail"

import CompanywalkingstatWalkingStatByCompanyManager from "./components/listers/CompanywalkingstatWalkingStatByCompanyCards"
import CompanywalkingstatWalkingStatByCompanyDetail from "./components/listers/CompanywalkingstatWalkingStatByCompanyDetail"

import ChallengeChallengeInfoManager from "./components/listers/ChallengeChallengeInfoCards"
import ChallengeChallengeInfoDetail from "./components/listers/ChallengeChallengeInfoDetail"
import ChallengeChallengeHstManager from "./components/listers/ChallengeChallengeHstCards"
import ChallengeChallengeHstDetail from "./components/listers/ChallengeChallengeHstDetail"

import CalculatepointPointStandardInfoManager from "./components/listers/CalculatepointPointStandardInfoCards"
import CalculatepointPointStandardInfoDetail from "./components/listers/CalculatepointPointStandardInfoDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/walkinghistories/walkingHsts',
                name: 'WalkinghistoryWalkingHstManager',
                component: WalkinghistoryWalkingHstManager
            },
            {
                path: '/walkinghistories/walkingHsts/:id',
                name: 'WalkinghistoryWalkingHstDetail',
                component: WalkinghistoryWalkingHstDetail
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
                path: '/userwalkingstats/walkingStatByUsers',
                name: 'UserwalkingstatWalkingStatByUserManager',
                component: UserwalkingstatWalkingStatByUserManager
            },
            {
                path: '/userwalkingstats/walkingStatByUsers/:id',
                name: 'UserwalkingstatWalkingStatByUserDetail',
                component: UserwalkingstatWalkingStatByUserDetail
            },

            {
                path: '/teamwalkingstats/walkingStatByTeams',
                name: 'TeamwalkingstatWalkingStatByTeamManager',
                component: TeamwalkingstatWalkingStatByTeamManager
            },
            {
                path: '/teamwalkingstats/walkingStatByTeams/:id',
                name: 'TeamwalkingstatWalkingStatByTeamDetail',
                component: TeamwalkingstatWalkingStatByTeamDetail
            },

            {
                path: '/companywalkingstats/walkingStatByCompanies',
                name: 'CompanywalkingstatWalkingStatByCompanyManager',
                component: CompanywalkingstatWalkingStatByCompanyManager
            },
            {
                path: '/companywalkingstats/walkingStatByCompanies/:id',
                name: 'CompanywalkingstatWalkingStatByCompanyDetail',
                component: CompanywalkingstatWalkingStatByCompanyDetail
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
                path: '/calculatepoints/pointStandardInfos',
                name: 'CalculatepointPointStandardInfoManager',
                component: CalculatepointPointStandardInfoManager
            },
            {
                path: '/calculatepoints/pointStandardInfos/:id',
                name: 'CalculatepointPointStandardInfoDetail',
                component: CalculatepointPointStandardInfoDetail
            },



    ]
})
