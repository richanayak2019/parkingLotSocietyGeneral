import { Component ,OnInit} from '@angular/core';
import { FormBuilder,FormGroup, FormControl, Validators, ValidatorFn , ValidationErrors} from '@angular/forms';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

/*
export interface parkingData {
    		'id' :number,
			'lot'	:  number,
			'parkingAmount':number,
			'parkingDuration': number,
			'vehicleNumber': number
	};
	*/
export class AppComponent implements OnInit  {

	public  vehicleForm: FormGroup;
	identityRevealedValidator: ValidatorFn;
	url:String = "http://localhost:8080/api" ;
	 parkings:any[] =[]  ; 
	
// Let Angular inject the control container
  constructor(private _fb: FormBuilder,private http: HttpClient) { }

  ngOnInit() {
  	this.createForm();
  	 this.getListParkings();
  }
  createForm() {


	  this.vehicleForm = new FormGroup({
	  		 
			'vehicleLot'	:  new FormControl(),
			'vehicleAmount': new FormControl(),
			'vehicleDuration': new FormControl(),
			'vehicleNumber': new FormControl()
		}, { });
	  
	  


	}
  // your code goes here
 

	onSubmit() {
	  console.log('reactiveForm' , this.vehicleForm.value);
	  const parkingAmount = this.vehicleForm.get('vehicleAmount').value;
		const parkingDuration = this.vehicleForm.get('vehicleDuration');

		let calculatedCost :number =0 ;
		if(parkingDuration.value > 60 ){
		    calculatedCost = 20 + (parkingDuration.value-60)*0.333;
		}
		else{
		    calculatedCost = 20;
		}

		if(calculatedCost < parkingAmount){

		this.newParking(this.vehicleForm.value);
		console.log('reactiveForm' , this.vehicleForm.value);
		}
		else{
			console.log('error insuffiient amount' , this.vehicleForm.value);
		}
	}


  public getListParkings():any{
     this.http.get(this.url+"/parkings").subscribe((res : any[])=>{
        console.log(res);
        this.parkings = res;
        });
  }

  public newParking(parkingRequest):any{

  	let data = {
  		'lot'	:  parkingRequest.vehicleLot,
			'parkingAmount':parkingRequest.vehicleAmount,
			'parkingDuration': parkingRequest.vehicleDuration,
			'vehicleNumber': parkingRequest.vehicleNumber
  	}
    return this.http.post(this.url+"/parkings" ,data).subscribe( (val) => {
            console.log("POST call successful value returned in body", 
                        val);
        },
        response => {
            console.log("POST call in error", response);
        },
        () => {
            console.log("The POST observable is now completed.");
        })
  }
	
}

	
 