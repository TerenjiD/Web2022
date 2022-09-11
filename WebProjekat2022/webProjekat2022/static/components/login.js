Vue.component("login",{
    data : function(){
        return{
            loginDTO:{username:null,password:null,role:null},
            facilitySearchDTO:{name:null,type:null,location:null,rating:null,sortType:null,sortBy:null,filterBy:null},
            facilities : null
        }
    },
    template:`
    <div>
        <form method="post">
            <table>
                <tr><td>Username</td><td><input type="text" v-model="loginDTO.username" id="username"  /></td></tr>
                <tr><td>Password</td><td><input type="password" v-model="loginDTO.password" id="password"/></td></tr>
                <tr><td><button v-on:click = "loginUser"  style="padding: 7px 20px;
                background-color: aqua;">Login in</button></td></tr>
                
            </table>
            
        </form>
        <button v-on:click = "registerUser" style="padding: 7px 20px;
        background-color: aqua;">Register</button>
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
                    <td><img :src="p.logo" width="50" height="50"></td>
                    <td>{{p.location.address.street}},{{p.location.address.number}},{{p.location.address.city}},{{p.location.address.country}}</td>
                    <td>{{p.rating}}</td>
                    <td>{{p.workingHours}}</td>
            	</tr>
            </table>
            <div>
            <button v-on:click = "Test"  style="padding: 7px 20px;
                background-color: aqua;">Test</button>
            </div>
    </div>
    `,
     mounted () {
            axios
               .get('rest/facilities/')
               .then(response => (this.facilities = response.data))
    },
    methods : {
        loginUser : function(event){
            
            axios.post('rest/customerHomePage',this.loginDTO).then(response =>{
                if(response.data != null){
                    
                    let check = response.data.role;
                    
                    if(check == "CUSTOMER"){
                        router.push('/customerHomePage/');
                        alert("Uspesno logovanje");
                    }else if(check == "MANAGER"){
                        router.push('/managerHomePage/');
                        alert("Uspesno logovanje");
                    }else if(check == "COACH"){
                        router.push('/coachHomePage/');
                        alert("Uspesno logovanje");
                    }else if(check == "ADMIN"){
                        router.push('/adminHomePage/');
                        alert("Uspesno logovanje");
                    }

                }
        }).catch(error => alert("Neuspesno logovanje"));
            event.preventDefault();
        },

        registerUser : function(){
            router.push('/register/')
        },

        search : function(event){
            axios
            .post("rest/facilities/search/",this.facilitySearchDTO )
            .then(response => (this.facilities = response.data))
            event.preventDefault();
        },

        Test : function(){
            router.push('managerForFacility')
        }

    }
})