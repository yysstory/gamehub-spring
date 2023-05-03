1. 인증필요

게임등록 
POST /api/web/user/game
gameName 게임명

게임불러오기(리스트)
GET /api/web/user/game

게임불러오기(상세) 
GET /api/web/user/game/{id}


2. 인증 불필요

회원가입 
POST /api/web/signup
    userName (사용자 이름)
    password (비밀번호)

로그인 
POST /api/web/login
    username (사용자 이름)
    password (비밀번호)


플레이 저장: POST /api/play
    gameId: 22 // 필수o
    apiKey: key, //필수o 
    playerName: "용식이", // 필수x
    playerComment: "1등이다", // 필수x
    score: "점수", //필수o, 1~9999999
    playTime: 12, //필수x, 1~222222 (sec) 
    playId:  29292101,//필수x 중복저장 방지용(같은 값이면 두번저장 안함 타임스탬프 추천)
    etcTxt: "게임결과데이터"

랭킹불러오기 GET /api/ranking
    gameId: 22 // 필수o
    apiKey: key, //필수o 
    timeUnit: "day" //필수x  day, week, month(기본값) 선택