import axios from "axios";

export default {
    list() {
        return axios.get('/playlists');
    },
    getByEventId(eventId) {
        return axios.get(`/playlists?eventId=${eventId}`)
    },
    get(id) {
        return axios.get(`/playlists/${id}`);
    },
    add(playlist) {
        return axios.post('/playlists', playlist);
    },
    update(id, playlist) {
        return axios.put(`/playlists/${id}`, playlist);
    },
    delete(id) {
        return axios.delete(`/playlists/${id}`);
    }
}