import type { FC, ReactNode } from 'react';

export const combineComponents = (...components: FC<{children: ReactNode}>[]): FC<{children: ReactNode}> => {
  return components
    .reverse()
    .reduce(
      (AccumulatedComponents, CurrentComponent) => {
        return ({ children }): JSX.Element => {
          return (
            <AccumulatedComponents>
              <CurrentComponent>{children}</CurrentComponent>
            </AccumulatedComponents>
          );
        };
      },
      ({ children }) => <>{children}</>
    );
};
