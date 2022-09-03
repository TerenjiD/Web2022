Vue.component("customerHomePage",{
    data: function() {
        return {
            customer : [],
            facilities: null,
            input: null,
            facility : null,
            trainings : null
        }
    },
    template:`
    <div>
    <div>
                <div>
                    <p>Kupac prikeroni {{customer.name}}</p>
                    <button v-on:click="changeCustomer" style="padding: 7px 20px;
                    background-color: aqua;">Izmeni</button>
                <div>
                <div>
                <h4>Istorija treninga<h4>
                </div>
                <table v-for="(p,index) in trainings">
                        <tr>
                        <td>Ime:</td><td>{{p.name}}</td>
                        </tr>
                        <tr>
                        <td>Objekat:</td><td>{{p.facilityName}}</td>
                        </tr>
                        <tr>
                        <td>Datum treninga:</td><td>{{p.applicationDate}}</td>
                        </tr>
                        <tr>
                        <td>Tip:</td><td>{{p.type}}</td>
                        </tr>
                        <tr>
                        <td>Logo:</td><td>{{p.logo}}</td>
                        </tr>
                        <tr>
                        <td>Opis:</td><td>{{p.description}}</td>
                        </tr>
                        <tr>
                        <td>Trajanje:</td><td>{{p.duration}}</td>
                        </tr>
                        <tr>
                        <td>Trener:</td><td>{{p.coach}}</td>
                        </tr>
                </table>
                </div>
                </div>
        		<h3>Prikaz sportskih objekata</h3>
        		</div>
                <div>
                <template>
                    <form>
                    	<label>Pretraga</label>
                    	<input type = "text" v-model = "input" >
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
                        <td><button v-on:click="goIntoFacility(p)">Udji u objekat</button></td>
                	</tr>
                </table>
    <button v-on:click = "logoutUser" style="padding: 7px 20px;
    background-color: aqua;" >Log out</button>
    </div>
    `,
    mounted (event) {
        axios
        .get('rest/facilities/')
        .then(response => (this.facilities = response.data));
        axios.get('/rest/customerHomePage/customer').then(response => (this.customer = response.data));
        //za terenjija
        axios.get('/rest/customerHomePage/getDate')
        .then(response => (alert("Nije istekla clanarina")))
        .catch(error => (alert("Istekla clanarina")))
        axios.get('/rest/customerHomePage/getTrainingHistory')
        .then(response => (this.trainings = response.data))
        //za terenjija
        //ja
        axios.get('rest/customerHomePage/checkComment').then(response =>{
            alert("Prvi put u objektu")
        }).cathc(error =>{
            alert("Nije prvi put u objektu")
        })
        //ja
    },
    methods: {
        logoutUser : function(){
            router.push("/")
        },
        search : function(){
            if(this.input == ""){
                axios
                .get('rest/facilities/')
                .then(response => (this.facilities = response.data))
            }else
                axios
                .get("rest/facilities/search/" + this.input)
                .then(response => (this.facilities = response.data))
        },
        changeCustomer : function(){
            router.push('/changeInfoCustomer/')
        },
        goIntoFacility : function(p){
            axios.post('rest/customerHomePage/FacilityOpen',p).then(response => {
                alert("Uspesan ulazak")
                router.push('/customerHomePage/FacilityOpen')
            }).catch(error => (alert("Neuspesan ulazak")))
        }
    }
})