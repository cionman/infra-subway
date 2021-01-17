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

export default () => {

  let stationRes = http.get(`${BASE_URL}/stations`);

  check(stationRes, {
    'retrieved station :': (resp) => resp.json('accessToken') !== '',
  });

};