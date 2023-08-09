import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});

export default {
    list() {
        return http.get('/songs');
    },
    get(id) {
        return http.get(`songs/${id}`);
    },
    add(song) {
        return http.post('/songs', song);
    },
    update(id, song) {
        return http.put(`/songs/${id}`, song);
    },
    delete(id) {
        return http.delete(`/songs/${id}`);
    }
}