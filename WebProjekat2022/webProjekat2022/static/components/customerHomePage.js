Vue.component("customerHomePage",{
    data: function() {
        return {
            customer : [],
            facilities: null,
            facilitySearchDTO:{name:null,type:null,location:null,rating:null,sortType:null,sortBy:null,filterBy:null},
            facility : null,
            trainingSearchDTO:{name:null,cost:null,date:null,sortType:null,sortBy:null,filterBy:null},
            trainings : null
        }
    },
    template:`

    <div>
                    <p>Kupac prikeroni {{customer.name}}</p>
                    <button v-on:click="logout" style="padding: 7px 20px;
                    background-color: aqua;">Izloguj se</button><br>
                    <button v-on:click="buyFitPass" style="padding: 7px 20px;
                    background-color: aqua;">Kupi clanarinu</button><br>
                    <button v-on:click="changeCustomer" style="padding: 7px 20px;
                    background-color: aqua;">Izmeni</button>
                
                <h4>Istorija treninga</h4>

                <div>
                        <p>Kupac prikeroni {{customer.name}}</p>
                        <button v-on:click="logout" style="padding: 7px 20px;
                        background-color: aqua;">Izloguj se</button><br>
                        <button v-on:click="changeCustomer" style="padding: 7px 20px;
                        background-color: aqua;">Izmeni</button>
                    </div>

                    <div>
                            <template>
                            <form>
                            <h4>Pretraga treninga</h4>
                            <br>
                            <label>Sportski objekat</label>
                            <input type = "text" v-model = "trainingSearchDTO.name" >
                            <label>Datum</label>
                            <input type = "text" v-model = "trainingSearchDTO.date" >
                            <br>
                            <h4>Sortiranje</h4>
                            <label>Sortiraj na osnovu</label>
                            <select v-model="trainingSearchDTO.sortBy" name="sortMenu" id="sortMenu">
                                <option value="0" selected default>Bez sortiranja</option>
                                <option value="1">Po nazivu objekta</option>
                                <option value="2">Po datumu</option>
                            </select>
                            <label>Redosled</label>
                            <select v-model="trainingSearchDTO.sortType" name="sortMenuT" id="sortMenuT">
                                <option value="1" selected>Rastuce</option>
                                <option value="2">Opadajuce</option>
                            </select>
                            <br>
                            <h4>Filtriranje</h4>
                            <label >Filtriraj na osnovu</label>
                            <select v-model="trainingSearchDTO.filterBy" name="filterMenu" id="filterMenu">
                                <option value="0" selected default>Bez filtriranja</option>
                                <optgroup label="Po tipu objekta">
                                    <option value="1">Teretana</option>
                                    <option value="2">Bazen</option>
                                    <option value="3">Sportski centar</option>
                                    <option value="4">Plesni studio</option>
                                </optgroup>
                                <optgroup label="Po tipu treninga">
                                    <option value="5">Grupni</option>
                                    <option value="6">Personalni</option>
                                    <option value="7">Teretana</option>
                                </optgroup>
                            </select>
                        <br>
                        <button @click="searchTrainings" >Pretrazi</button>
                        </form>
                        </template>
                    </div>

                    <div>
                        <h4>Istorija treninga</h4>
                        </div>
                        <div>
                        <table border="1">
                        <tr bgcolor="lightgrey">
                            <th>Ime</th>
                            <th>Objekat</th>
                            <th>Datum</th>
                            <th>Tip</th>
                            <th>Logo</th>
                            <th>Opis</th>
                            <th>Trajanje</th>
                            <th>Trener</th>
                        </tr>
                        <tr v-for="(p, index) in trainings" >
                            <td>{{p.name}}</td>
                            <td>{{p.facilityName}}</td>
                            <td>{{p.applicationDate}}</td>
                            <td>{{p.type}}</td>
                            <td>{{p.logo}}</td>
                            <td>{{p.description}}</td>
                            <td>{{p.duration}}</td>
                            <td>{{p.coach}}</td>
                        </tr>
                        </table>
                    </div>

                    <h3>Prikaz sportskih objekata</h3>

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
                                        <option value="0" selected default>Bez sortiranja</option>
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
                                        <option value="0" selected default>Bez filtriranja</option>
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

                            <div>
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
                                        <td><button v-on:click="goIntoFacility(p)">Udji u objekat</button></td>
                                        <td><button v-on:click="viewComments(p)">Komentari</button></td>
                                	</tr>
                                </table>
                			</div>
            </div>
    `,
    mounted (event) {
        axios
        .get('rest/facilities/')

        .then(response => (this.facilities = response.data));
        axios.get('/rest/customerHomePage/customer').then(response => (this.customer = response.data));
        axios.get('/rest/customerHomePage/getDate')
        .then(response => (alert("Nije istekla clanarina")))
        .catch(error => (alert("Istekla clanarina")))
        axios.get('/rest/customerHomePage/getTrainingHistory')
        .then(response => (this.trainings = response.data))
        axios.get('rest/customerHomePage/checkComment').then(response =>{
            alert("Prvi put u objektu")
            router.push('customerHomePage/putComment')
        }).catch(error =>{
            alert("Nije prvi put u objektu")
        })
    },
    methods: {
        logoutUser : function(){
            router.push("/")
        },
        searchTrainings: function(event){
               axios
               .post("rest/training/search/",this.trainingSearchDTO )
               .then(response => (this.trainings = response.data))
               event.preventDefault();
        },
        search : function(event){
               axios
               .post("rest/facilities/search/",this.facilitySearchDTO )
               .then(response => (this.facilities = response.data))
               event.preventDefault();
        },
        changeCustomer : function(){
            router.push('/changeInfoCustomer/')
        },
        goIntoFacility : function(p){
            axios.post('rest/customerHomePage/FacilityOpen',p).then(response => {
                alert("Uspesan ulazak")
                router.push('/customerHomePage/FacilityOpen')
            }).catch(error => (alert("Neuspesan ulazak")))
        },
        viewComments : function(p){
            axios.post('rest/customerHomePage/setFacilityForComments',p).then(response => (router.push('/customerHomePage/viewCommentsForFacility')))
        },
        logout : function(event){
            axios.post('rest/customerHomePage/logout').then(response => {
                alert("Izlogovan si")
                router.push('/')
            })

        },
        buyFitPass : function(event){
            router.push('/customerHomePage/buyFitPass')
        }
    }
})