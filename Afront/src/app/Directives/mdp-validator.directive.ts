import { Directive, Input } from '@angular/core';
import { Validator, AbstractControl, NG_VALIDATORS, ValidatorFn } from '@angular/forms';

@Directive({
  selector: '[appMdpValidator]',
  providers: [{ provide: NG_VALIDATORS, useExisting: MdpValidatorDirective, multi: true }]
})
export class MdpValidatorDirective implements Validator {
  
  @Input('appMdpValidator') mdp: string;

  validate(control: AbstractControl): { [key: string]: any } | null {
    let result =  (this.mdp && this.mdp.length>6) ?  null : {"error" : {value: 'error password '}};
    return result;
  }

}

