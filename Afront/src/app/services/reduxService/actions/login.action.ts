import { createAction, props } from '@ngrx/store'

export const login = createAction(
  '[Login] Login',
  props<{ username: string; password: string }>()
);

export const auth = createAction(
  '[Login] Authenticated', props<{auth : boolean}>()
);



