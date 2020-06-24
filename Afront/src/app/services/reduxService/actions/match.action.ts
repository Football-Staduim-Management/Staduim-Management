import { createAction, props } from '@ngrx/store'
import { Match } from 'src/app/Model/Match';

export const reserveMatch = createAction(
    '[Match] Match',
    props<{ match : Match }>()
  );
