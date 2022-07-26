Vue.component("managerHomePage",{
    data : function(){
        return{
            userDTO : null,
            facilities : null,
            facilityDTO : null
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
        }
    }
    
}) 