import axios from "axios";

export default {
    list() {
        return axios.get('/playlist-song')
    },
    listSongsByPlaylistId(playlistId) {
        return axios.get(`/playlist-song/${playlistId}`)
    },
    add(playlistsong) {
        return axios.post('/playlist-song', playlistsong)
    }

}