import { createReducer, on } from '@ngrx/store'
import { User } from 'src/app/Model/user';
import * as loginActions from '../actions/login.action'
//import { Action } from 'rxjs/internal/scheduler/Action';

export const loginReducer = createReducer(
    null,
    on(loginActions.login, (state, parms) => (
        {
            ...state,
            email: parms.email,
            password: parms.password
        }
    )
    ),
    on(loginActions.auth, (state, parms) => (
        {
            ...state,
            authenticated: parms.authb,
            id: parms.user.id, 
            name: parms.user.name, 
            password: parms.user.password,
            email: parms.user.email,
            isAdmin: parms.user.isAdmin
        }
    )
    )
)

export function reducer(state, action) {

    return loginReducer(state, action);
} 