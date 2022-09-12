Vue.component("adminHomePage",{
    data : function(){
        return{
            adminDTO : null,
            usersSearchDTO:{name:null,surname:null,username:null,sortType:null,sortBy:null,filterBy:null},
            users : null

        }
    },
    template:`
    <div>
        <div>
            <p>Admin prikeroni {{adminDTO.name}}</p>
            <div>
            <button v-on:click="logout" style="padding: 7px 20px;
            background-color: aqua;">Izloguj se</button><br>
            </div>
            <button v-on:click="registerManager" style="padding: 7px 20px;
            background-color: aqua;">Napravi menadzera</button>
            <button v-on:click="registerCoach" style="padding: 7px 20px;
            background-color: aqua;">Napravi trenera</button>
            <button v-on:click="changeInfoAdmin" style="padding: 7px 20px;
            background-color: aqua;">Izmeni podatke</button>
            <button v-on:click="createFacility" style="padding: 7px 20px;
            background-color: aqua;">Napravi objekat sportski</button>
        </div>
        <div>
            <template>
                <form>
                    <h4>Pretraga</h4>
                    <br>
                	<label>Ime</label>
                	<input type = "text" v-model = "usersSearchDTO.name" >
                	<label>Prezime</label>
                    <input type = "text" v-model = "usersSearchDTO.surname" >
                	<label>Korisnicko ime</label>
                    <input type = "text" v-model = "usersSearchDTO.username" >
                    <br>
                    <h4>Sortiranje</h4>
                    <label>Sortiraj na osnovu</label>
                    <select v-model="usersSearchDTO.sortBy" name="sortMenu" id="sortMenu">
                        <option value="0" selected default>Bez sortiranja</option>
                        <option value="1">Po imenu</option>
                        <option value="2">Po prezimenu</option>
                        <option value="3">Po korsinickom imenu</option>
                        <option value="4">Po broju bodova</option>
                    </select>
                    <label>Redosled</label>
                    <select v-model="usersSearchDTO.sortType" name="sortMenuT" id="sortMenuT">
                        <option value="1" selected>Rastuce</option>
                        <option value="2">Opadajuce</option>
                    </select>
                    <br>
                    <h4>Filtriranje</h4>
                    <label >Filtriraj na osnovu</label>
                    <select v-model="usersSearchDTO.filterBy" name="filterMenu" id="filterMenu">
                        <option value="0" selected default>Bez filtriranja</option>
                        <optgroup label="Po ulozi">
                            <option value="1">Administrator</option>
                            <option value="2">Menadzer</option>
                            <option value="3">Trener</option>
                            <option value="4">Kupac</option>
                        </optgroup>
                        <optgroup label="Po tipu korisnkika">
                            <option value="5">Normal</option>
                            <option value="6">Silver</option>
                            <option value="7">Gold</option>
                        </optgroup>
                    </select>
                <br>
                <button @click="searchUsers" >Pretrazi</button>
                </form>
            </template>
            <button v-on:click="viewComment" style="padding: 7px 20px;
            background-color: aqua;">Pregledaj komentare</button>
            <button v-on:click="viewComments" style="padding: 7px 20px;
            background-color: aqua;">Pregledaj sve komentare</button>
        </div>
        <div>
            <button v-on:click="createPromoCode" style="padding: 7px 20px;
            background-color: aqua;">Napravi promo kod</button>
        </div>
        <div>
            <table border="1">
            <tr bgcolor="lightgrey">
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Pol</th>
                    <th>Datum Rodjenja</th>
                    <th>Uloga</th>
                    <th>Korisnicko ime</th>
            </tr>
            <tr v-for="(p, index) in users" >
                <td>{{p.name}}</td>
                <td>{{p.lastName}}</td>
                <td>{{p.gender}}</td>
                <td>{{p.dateOfBirth}}</td>
                <td>{{p.role}}</td>
                <td>{{p.username}}</td>
                </tr>
            </table>
        </div>
    </div>
    `,mounted(){
        axios.get('/rest/adminHomePage/admin').then(response => (this.adminDTO=response.data));
        axios.get('/rest/adminHomePage/showUsers').then(response => (this.users = response.data));
    },
    methods : {
        registerManager : function(){
            router.push('/adminHomePage/registerManager/');
        },
        registerCoach : function(){
            router.push('/adminHomePage/registerCoach/');
        },
        changeInfoAdmin : function(event){
            router.push('/adminHomePage/changeInfoAdmin/')
        },
        searchUsers : function(event){
                    axios
                    .post('/adminHomePage/search/',this.usersSearchDTO )
                    .then(response => (this.users = response.data))
                    event.preventDefault();
        },
        createFacility : function(event){
            router.push('/adminHomePage/createFacility/')
        },
        viewComment : function(event){
            router.push('/adminHomePage/viewComments')
        },
        viewComments : function(event){
            router.push('/adminHomePage/commentsToViewAdmin')
        },
        createPromoCode : function(){
            router.push('/adminHomePage/definePromocode')
        },
        logout : function(event){
            axios.post('rest/adminHomePage/logout').then(response => {
                alert("Izlogovan si")
                router.push('/')
            })

        }
    }
    
}) 