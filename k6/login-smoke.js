import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  vus: 10, // 1 user looping for 1 minute
  duration: '10s',

  thresholds: {
    http_req_duration: ['p(99)<100'], // 99% of requests must complete below 1.5s
  },
};

const BASE_URL = 'https://www.cionman.p-e.kr';
const USERNAME = 'probitanima11@gmail.com';
const PASSWORD = '1';

export default () => {
  let loginRes = http.post(`${BASE_URL}/login/token`, {
    email: USERNAME,
    password: PASSWORD,
  });

  check(loginRes, {
    'logged in successfully': (resp) => resp.json('accessToken') !== '',
  });


  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };
  let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
  check(myObjects, { 'retrieved member': (obj) => obj.id != 0 });
  sleep(1);
};