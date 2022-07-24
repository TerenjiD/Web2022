Vue.component("createFacility",{
    data : function(){
        return{
            objectDTO : {name:null,facilityType:null,contentType:null,status:null,logo:null,
                latitude:null,longitude:null,street:null,number:null,city:null,country:null,
                workingHours:null,rating:null,manager:null},
            managerDTO : null
        }
    },
    template:
    `
    <div>
    <form method="post">
        <table>
            <tr><td>Naziv</td>
                <td><input type="text" id="name" v-model="objectDTO.name"></td>
            </tr>
            <tr><td>Tip</td>
            <td><select type="text" value="" id="facilityType" v-model="objectDTO.facilityType">
                <option>GYM</option>
                <option>POOL</option>
                <option>SPORT_CENTER</option>
                <option>DANCE_STUDIO</option>
            </select></td>
            </tr>
            <tr><td>Sadrzaj</td>
            <td><input type="text" id="contentType" v-model="objectDTO.contentType"></td>
            </tr>
            <tr><td>Status</td>
            <td><input type="text" id="status" v-model="objectDTO.status"></td>
            </tr>
            <tr><td>Logo</td>
            <td><input type="text" id="logo" v-model="objectDTO.logo"></td>
            </tr>
            <tr><td>latitude</td>
            <td><input type="text" id="latitude" v-model="objectDTO.latitude"></td>
            </tr>
            <tr><td>longitude</td>
            <td><input type="text" id="longitude" v-model="objectDTO.longitude"></td>
            </tr>
            <tr><td>Ulica</td>
            <td><input type="text" id="street" v-model="objectDTO.street"></td>
            </tr>
            <tr><td>Broj ulice</td>
            <td><input type="text" id="number" v-model="objectDTO.number"></td>
            </tr>
            <tr><td>Grad</td>
            <td><input type="text" id="city" v-model="objectDTO.city"></td>
            </tr>
            <tr><td>Drzava</td>
            <td><input type="text" id="country" v-model="objectDTO.country"></td>
            </tr>
            <tr><td>Prosecna ocena</td>
            <td><input type="text" id="workingHours" v-model="objectDTO.workingHours"></td>
            </tr>
            <tr><td>Radno vreme</td>
            <td><input type="text" id="rating" v-model="objectDTO.rating"></td>
            </tr>
            <tr><td>Menadzer</td>
            <td><input type="text" id="manager" v-model="objectDTO.manager"></td>
            </tr>
            <tr><td></td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </form>
    </div>
    `,
    mounted(){

    },
    methods : {

    }
})