import axios from 'axios';

export default {
    getAllExamples() {
        return axios.get('/examples');
    }
}