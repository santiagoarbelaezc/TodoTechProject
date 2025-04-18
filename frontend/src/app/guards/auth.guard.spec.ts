import { TestBed } from '@angular/core/testing';
import {CanActivateFn, Router} from '@angular/router';

import { AuthGuard } from './auth.guard';
import {UsuarioService} from "../services/usuario.service";

describe('AuthGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
      TestBed.runInInjectionContext(() => new AuthGuard(TestBed.inject(UsuarioService), TestBed.inject(Router)).canActivate());  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
