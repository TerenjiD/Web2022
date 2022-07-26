Vue.component("login",{
    data : function(){
        return{
            loginDTO:{username:null,password:null,role:null},
            facilities : null,
            input : null
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

        Test : function(){
            router.push('managerForFacility')
        }

    }
})