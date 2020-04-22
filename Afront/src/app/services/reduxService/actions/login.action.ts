import { createAction, props } from '@ngrx/store'
import { User } from 'src/app/Model/user';

export const login = createAction(
  '[Login] Login',
  props<{ email : string, password : string }>()
);

export const auth = createAction(
  '[Login] Authenticated', props<{authb : boolean, user : User}>()
);



