Vue.component("managerHomePage",{
    data : function(){
        return{
            userDTO : null,
            facilities : null,
            facilityDTO : null,
            contentToShow : null,
            customers : null,
            coaches : null,
            facilitySearchDTO:{name:null,type:null,location:null,rating:null,sortType:null,sortBy:null,filterBy:null},
            trainingSearchDTO:{name:null,date:null,sortType:null,sortBy:null,filterBy:null}
        }
    },
    template:`
    <div>
        <p>Menadzer prikeroni {{userDTO.name}}</p>
        <button v-on:click="logout" style="padding: 7px 20px;
                    background-color: aqua;">Izloguj se</button><br>
        <button v-on:click="changeInfo" style="padding: 7px 20px;
                        background-color: aqua;">Izmeni</button>
        <div>
        <div>
        <button v-on:click="viewComments" style="padding: 7px 20px;
        background-color: aqua;">Komentari</button>
        </div>
        <p>Objekat od ovog menadzera : {{facilityDTO.name}}</p>
        <table>
            <tr><td>Facility type:</td>
                <td>{{facilityDTO.facilityType}}</td>
            </tr>
            <tr><td>Content type:</td>
                <td>{{facilityDTO.contentType}}</td>
            </tr>
            <tr><td>Facility status:</td>
                <td>{{facilityDTO.status}}</td>
            </tr>
            <tr><td>Logo:</td>
                <td>{{facilityDTO.logo}}</td>
            </tr>
            <tr><td>Location:</td>
                <td>{{facilityDTO.latitude}} {{facilityDTO.longitude}} {{facilityDTO.street}} {{facilityDTO.number}}
                {{facilityDTO.city}} {{facilityDTO.country}}
                </td>
            </tr>
            <tr><td>Working hours:</td>
                <td>{{facilityDTO.workingHours}}</td>
            </tr>
            <tr><td>Rating:</td>
                <td>{{facilityDTO.rating}}</td>
            </tr>

        </table>
        <div>
                        <table >
                            <tr><td>Korisnici:</td><td v-for="(p,index) in customers">{{p.name}} {{p.lastName}}</td></tr>
                        </table>
        </div>
        <div>
                        <table >
                            <tr><td>Treneri:</td><td v-for="(p,index) in coaches">{{p.name}} {{p.lastName}}</td></tr>
                        </table>
        </div>
        <button v-on:click="addContent" style="padding: 7px 20px;
                        background-color: aqua;">Dodaj sadrzaj</button>

        <table border="1">
        <tr bgcolor="lightgrey">
                    	    <th>Naziv</th>
                    	    <th>Tip</th>
                    	    <th>Trener</th>
                           	<th>Logo</th>
                           	<th>Opis</th>
                           	<th>Trajanje</th>
                           	<th></th>
                            <th></th>
        </tr>
        <tr v-for="(p, index) in contentToShow" >
                    	    <td>{{p.name}}</td>
                    	    <td>{{p.type}}</td>
                    	    <td>{{p.coachID}}</td>
                            <td><img :src="p.logo" width="50" height="50"></td>
                            <td>{{p.description}}</td>
                            <td>{{p.duration}}</td>
                            <td><button v-on:click="changeContent(p)" style="padding: 7px 20px;
                                     background-color: aqua;">Izmeni sadrzaj</button></td>
                            <td><button v-on:click="addCoachToContent(p)" style="padding: 7px 20px;
                                     background-color: aqua;">Dodaj trenera</button></td>
        </tr>
        </table>
        <div>
            <table></table>
        </div>
        </div>
                        <div>
                        <h3>Prikaz sportskih objekata</h3>
                        </div>
                        <div>
                                    <template>
                                        <form>
                                            <h4>Pretraga</h4>
                                            <br>
                                        	<label>Naziv</label>
                                        	<input type = "text" v-model = "facilitySearchDTO.name" >
                                        	<label>Tip</label>
                                            <input type = "text" v-model = "facilitySearchDTO.type" >
                                        	<label>Lokacija</label>
                                            <input type = "text" v-model = "facilitySearchDTO.location" >
                                            <label>Ocena</label>
                                            <input type = "text" v-model = "facilitySearchDTO.rating" >
                                            <br>
                                            <h4>Sortiranje</h4>
                                            <label>Sortiraj na osnovu</label>
                                            <select v-model="facilitySearchDTO.sortBy" name="sortMenu" id="sortMenu">
                                                <option value="0" selected>Bez sortiranja</option>
                                                <option value="1">Po nazivu</option>
                                                <option value="2">Po lokaciji</option>
                                                <option value="3">Po prosecnoj oceni</option>
                                            </select>
                                            <label>Redosled</label>
                                            <select v-model="facilitySearchDTO.sortType" name="sortMenuT" id="sortMenuT">
                                                <option value="1" selected>Rastuce</option>
                                                <option value="2">Opadajuce</option>
                                            </select>
                                            <br>
                                            <h4>Filtriranje</h4>
                                            <label >Filtriraj na osnovu</label>
                                            <select v-model="facilitySearchDTO.filterBy" name="filterMenu" id="filterMenu">
                                                <option value="0" selected>Bez filtriranja</option>
                                                <optgroup label="Po tipu objekta">
                                                    <option value="1">Teretana</option>
                                                    <option value="2">Bazen</option>
                                                    <option value="3">Sportski centar</option>
                                                    <option value="4">Plesni studio</option>
                                                </optgroup>
                                                <optgroup label="Po dostupnosti">
                                                    <option value="5">Otvoren</option>
                                                    <option value="6">Zatvoren</option>
                                                </optgroup>
                                            </select>
                                        <br>
                                        <button @click="search" >Pretrazi</button>
                                        </form>
                                    </template>
                                    </div>
                        <table border="1">
                            <tr bgcolor="lightgrey">
                                <th>Naziv</th>
                                <th>Tip</th>
                                <th>Sadrzaj</th>
                                   <th>Status</th>
                                   <th>Logo</th>
                                   <th>Lokacija</th>
                                   <th>Prosecna ocena</th>
                                   <th>Radno vreme</th>
                            </tr>
                            <tr v-for="(p, index) in facilities" >
                                <td>{{p.name}}</td>
                                <td>{{p.facilityType}}</td>
                                <td>{{p.contentType}}</td>
                                <td>{{p.status}}</td>
                                <td>{{p.logo}}</td>
                                <td>{{p.location.address.street}},{{p.location.address.number}},{{p.location.address.city}},{{p.location.address.country}}</td>
                                <td>{{p.workingHours}}</td>
                                <td>{{p.rating}}</td>
                            </tr>
                        </table>

    </div>
    `,
    mounted(){
        axios.get('/rest/managerHomePage/manager').then(response => (this.userDTO = response.data));
        axios
               .get('rest/facilities/')
               .then(response => (this.facilities = response.data));
        axios.get('/rest/managerHomePage/getFacility').then(response => (this.facilityDTO = response.data));
        axios.get('/rest/managerHomePage/getContent/').then(response => (this.contentToShow = response.data));
        axios.get('rest/managerHomePage/GetCustomers').then(response => (this.customers = response.data))
        axios.get('rest/managerHomePage/GetCoachesForSpecificFacility').then(response => (this.coaches = response.data))
    },
    methods : {
        changeInfo : function() {
            router.push('/managerHomePage/changeInfoManager/');
        },
        search : function(event){
                    axios
                    .post("rest/facilities/search/",this.facilitySearchDTO )
                    .then(response => (this.facilities = response.data))
                    event.preventDefault();
        },
        addContent : function(event){
            router.push('/managerHomePage/addContent/')
        },
        changeContent : function(p){
            axios.post('rest/managerHomePage/setContent/',p);
            router.push('/managerHomePage/changeContent/');
        },
        addCoachToContent : function(p){
            axios.post('rest/managerHomePage/setContent/',p);
            router.push('/managerHomePage/addCoachToTraining');
        },
        viewComments : function(){
            router.push('/managerHomePage/commentsToViewManager')
        },
        logout : function(event){
            axios.post('rest/managerHomePage/logout').then(response => {
                alert("Izlogovan si")
                router.push('/')
            })
        }
    }
    
}) 