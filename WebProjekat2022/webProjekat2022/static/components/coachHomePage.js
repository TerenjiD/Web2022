Vue.component("coachHomePage",{
    data : function(){
        return{
            coachDTO : null,
            trainingSearchDTO:{name:null,date:null,sortType:null,sortBy:null,filterBy:null},
            trainingsToDisplay : null
        }
    },
    template:`
    <div>
        <p>Trener prikeroni {{coachDTO.name}}</p>
        <button v-on:click="logout" style="padding: 7px 20px;
                    background-color: aqua;">Izloguj se</button><br>
        <button v-on:click="changeInfoCoach" style="padding: 7px 20px;
                        background-color: aqua;">Izmeni</button>
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
                        <h4>Treninzi</h4>
                        </div>
                        <div>
                        <table border="1">
                        <tr bgcolor="lightgrey">
                            <th>Naziv objekta</th>
                            <th>Naziv treninga</th>
                            <th>Tip treninga</th>
                            <th>Opis</th>
                            <th>Datum</th>
                            <th>Pocetak</th>
                            <th>Kraj</th>
                        </tr>
                        <tr v-for="(p, index) in trainingsToDisplay" >
                            <td>{{p.facilityName}}</td>
                            <td>{{p.name}}</td>
                            <td>{{p.type}}</td>
                            <td>{{p.description}}</td>
                            <td>{{p.duration}}</td>
                            <td>{{p.startTime}}</td>
                            <td>{{p.endTime}}</td>
                        </tr>
                        </table>
        </div>
    </div>
    `,mounted(){
        axios.get('/rest/coachHomePage/coach').then(response => (this.coachDTO=response.data));
        axios.get('rest/coachHomePage/getTrainings').then(response => (this.trainingsToDisplay=response.data))
    },
    methods : {
        changeInfoCoach : function(event){
            router.push('/coachHomePage/changeInfoCoach/');
        },
        searchTrainings: function(event){
            axios
            .post("rest/training/searchCoach/",this.trainingSearchDTO )
            .then(response => (this.trainingsToDisplay = response.data))
            event.preventDefault();
        },
        viewTraining : function(p,event){
            axios.post('rest/coachHomePage/viewTraining',p).then(response => (router.push('/coachHomePage/view')))
        },
        logout : function(event){
            axios.post('rest/coachHomePage/logout').then(response => {
                alert("Izlogovan si")
                router.push('/')
            })
        }
    }
    
}) 