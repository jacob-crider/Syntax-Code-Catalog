import axios from "axios";

export default {
    compile(execution) {
        return axios.post('https://api.jdoodle.com/v1/execute', execution);
    }
}