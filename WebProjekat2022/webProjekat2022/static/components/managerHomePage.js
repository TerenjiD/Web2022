Vue.component("managerHomePage",{
    data : function(){
        return{
            userDTO : null,
            facilities : null,
            facilityDTO : null,
            contentToShow : null
        }
    },
    template:`
    <div>
        <p>Menadzer prikeroni {{userDTO.name}}</p>
        <button v-on:click="changeInfo" style="padding: 7px 20px;
                        background-color: aqua;">Izmeni</button>
        <div>
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
        <button v-on:click="addContent" style="padding: 7px 20px;
                        background-color: aqua;">Dodaj sadrzaj</button>
        <table v-for="(p, index) in contentToShow">
        <tr><td>Name:</td>
            <td>{{p.name}}</td>
        </tr>
        <tr><td>Type:</td>
            <td>{{p.type}}</td>
        </tr>
        <tr><td>Coach:</td>
            <td>{{p.coachID}}</td>
        </tr>
        <tr><td>Logo:</td>
            <td>{{p.logo}}</td>
        </tr>
        <tr><td>Description:</td>
            <td>{{p.description}}</td>
        </tr>
        <tr><td>Duration:</td>
            <td>{{p.duration}}</td>
        </tr>
        <tr>
            <td><button v-on:click="changeContent" style="padding: 7px 20px;
                        background-color: aqua;">Izmeni sadrzaj</button></td>
            <td><button v-on:click="addCoachToContent(p)" style="padding: 7px 20px;
                        background-color: aqua;">Dodaj trenera</button></td>
            </tr>
        </table>
        </div>
                        <div>
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
    },
    methods : {
        changeInfo : function() {
            router.push('/managerHomePage/changeInfoManager/');
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
        addContent : function(event){
            router.push('/managerHomePage/addContent/')
        },
        changeContent : function(event){

        },
        addCoachToContent : function(p){
            axios.post('rest/managerHomePage/setContent/',p);
            router.push('/managerHomePage/addCoachToTraining');
        }
    }
    
}) 