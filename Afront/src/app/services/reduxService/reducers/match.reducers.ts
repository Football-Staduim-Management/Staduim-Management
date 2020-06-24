import { createReducer, on } from '@ngrx/store'
import * as matchActions from '../actions/match.action'

export const matchReducer = createReducer(
    null,
    on(matchActions.reserveMatch,(state, parms)=>({
        ...state,
        match : parms.match
    }))
)

export function mReducer(state,action){
    return matchReducer(state,action);
}
